package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidBookingException;
import com.application.bookMyShow.Exceptions.InvalidSeatException;
import com.application.bookMyShow.adapter.paymentGatewayAdapter.PaymentGatewayAdapter;
import com.application.bookMyShow.adapter.paymentGatewayAdapter.PaymentGatewayStrategy;
import com.application.bookMyShow.models.Booking;
import com.application.bookMyShow.models.Payment;
import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.models.enums.*;
import com.application.bookMyShow.repositories.BookingRepository;
import com.application.bookMyShow.repositories.PaymentRepository;
import com.application.bookMyShow.repositories.ShowSheetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.StripeClient;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.v2.billing.MeterEventStreamCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PaymentService {

    @Value("${stripe.webhook.secret}")
    private String stripeWebhookSecret;

    private BookingRepository bookingRepository;
    private PaymentRepository paymentRepository;
    private ShowSheetRepository showSheetRepository;

    public PaymentService(BookingRepository bookingRepository,PaymentRepository paymentRepository,ShowSheetRepository showSheetRepository){
        this.bookingRepository=bookingRepository;
        this.paymentRepository=paymentRepository;
        this.showSheetRepository=showSheetRepository;
    }

    public String createPaymentLink(Long bookingId){
        //1. Get the order details from order service
        // restTemplate.getForDetails("https://orderservice/orders/orderId");

        Optional<Booking> booking=bookingRepository.findById(bookingId);
        if(booking.isEmpty()){
            throw new InvalidBookingException("Invalid Booking");
        }
        Long price=Long.valueOf(booking.get().getAmount()+"00");
        //2. Get payment Gateway based upon a strategy
        PaymentGatewayAdapter paymentGatewayAdapter= PaymentGatewayStrategy.getPaymentGatewayAdapter(PaymentGateway.STRIPE); //Do not pass stripe only, better to read from dp using orderId

        //3. Call the payment Gateway to create payment link
        String url="";
        try{
            url=paymentGatewayAdapter.createPaymentLink(price,bookingId);
        }catch (Exception e){
            e.printStackTrace();
            //throw new InvalidPaymentLinkCreationException("Exception while creating payment link");
        }
        //4 return payment link
        return url;
    }

    public ResponseEntity<String> stripeWebhooksEvent(String payload, String sigHeader) {
        Event event;
        try{
            event= Webhook.constructEvent(payload,sigHeader,stripeWebhookSecret);
            Payment payment=new Payment();
            // Get the data object
            EventDataObjectDeserializer deserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (deserializer.getObject().isPresent()) {
                stripeObject = deserializer.getObject().get();
            } else {
                // Handle deserialization failure (API version mismatch etc.)
                System.out.println("⚠️ Could not deserialize event data");
                return ResponseEntity.badRequest().build();
            }

            // Now decide based on event type
            switch (event.getType()) {
                case "payment_intent.succeeded":
                    PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                    System.out.println("✅ Payment succeeded: " + paymentIntent.getId());
                    payment.setTransactionId(paymentIntent.getId());
                    payment.setAmount(paymentIntent.getAmount());
                    payment.setPaymentStatus(PaymentStatus.PAID);
                    payment.setPaymentMode(PaymentMode.CC);
                    System.out.println(paymentIntent.getMetadata().get("bookingId")+" Booking Id - ");
                    String bookingId=paymentIntent.getMetadata().get("bookingId");
                    if(bookingId==null||bookingId.isEmpty()){
                        throw new InvalidBookingException("Invalid Booking Id");
                    }
                    Optional<Booking> booking=bookingRepository.findById(Long.valueOf(bookingId));
                    if(booking.isEmpty()){
                        throw new InvalidBookingException("Invalid booking");
                    }
                    //Update status of show_sheets
                    List<ShowSheet> showSheets=booking.get().getShowSeats();
                    for(ShowSheet showSheet:showSheets){
                        if(showSheet.getShowSheetStatus()!= ShowSeatStatus.BLOCKED){
                            throw new InvalidSeatException("Invalid Seat Status");
                        }
                        showSheet.setShowSheetStatus(ShowSeatStatus.BOOKED);
                    }
                    showSheetRepository.saveAll(showSheets); //Update all show_sheets
                    booking.get().setBookingStatus(BookingStatus.CONFIRMED);
                    bookingRepository.save(booking.get()); // Update booking
                    payment.setBooking(booking.get());
                    paymentRepository.save(payment); //Save payment
                    //Send confirmation email

                    System.out.println("Amount: " + paymentIntent.getAmount());
                    System.out.println("Status: " + paymentIntent.getStatus());
                    break;

                case "payment_method.attached":
                    PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                    System.out.println("✅ PaymentMethod attached: " + paymentMethod.getId());
                    System.out.println("Type: " + paymentMethod.getType());
                    break;

                case "checkout.session.completed":
                    com.stripe.model.checkout.Session session1 = (com.stripe.model.checkout.Session) stripeObject;
                    System.out.println("✅ Checkout Session completed: " + session1.getId());
                    System.out.println("Customer Email: " + session1.getCustomerDetails().getEmail());
                    break;

                default:
                    System.out.println("⚠️ Unhandled event type: " + event.getType());
            }

        }catch (SignatureVerificationException e){
            log.error("SignatureVerificationException (WebhookEvent) {}", e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
