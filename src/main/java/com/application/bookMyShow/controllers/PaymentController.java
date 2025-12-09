package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.paymentDto.CreatePaymentLinkRequestDto;
import com.application.bookMyShow.dtos.paymentDto.CreatePaymentLinkResponseDto;
import com.application.bookMyShow.services.PaymentService;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/stripe/createPaymentLink")
    public ResponseEntity<CreatePaymentLinkResponseDto> createPaymentLink(@RequestBody CreatePaymentLinkRequestDto request){
        CreatePaymentLinkResponseDto response=new CreatePaymentLinkResponseDto();
        response.setUrl(paymentService.createPaymentLink(request.getBookingId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/stripe/webhooks")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader(value = "Stripe-Signature", required = false) String sigHeader) {
        System.out.println("Payment Webhooks");
        return paymentService.stripeWebhooksEvent(payload,sigHeader);
    }
}
