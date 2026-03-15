package com.application.bookMyShow.adapter.paymentGatewayAdapter.stripe;

import com.application.bookMyShow.adapter.paymentGatewayAdapter.PaymentGatewayAdapter;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGatewayAdapter implements PaymentGatewayAdapter {
    @Override
    public String createPaymentLink(Long price,Long bookingId,Long userId) throws Exception {

//        System.out.println(String.valueOf(bookingId)+" - "+bookingId);
//        System.out.println(Long.valueOf(String.valueOf(bookingId))+" - "+bookingId);

        ProductCreateParams productParams =
                ProductCreateParams.builder()
                        .setDescription("(created by Stripe)")
                        .setName("Movie Ticket")
                        .setActive(true)
                        .setDefaultPriceData(
                                ProductCreateParams.DefaultPriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount(price)
                                        .build()
                        )
                        .build();

        Product product = Product.create(productParams);

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(price)
                        .setProduct(product.getId())
                        .build();

        Price stripePrice = Price.create(priceParams);

        PaymentLinkCreateParams paymentLinkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(stripePrice.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("http://localhost:5173/my-bookings/"+userId)
                                                        .build()
                                        )
                                        .build()
                        ).setPaymentIntentData(
                                PaymentLinkCreateParams.PaymentIntentData.builder()
                                        .putMetadata("bookingId",String.valueOf(bookingId))
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkParams);
        return paymentLink.getUrl();
    }
}
