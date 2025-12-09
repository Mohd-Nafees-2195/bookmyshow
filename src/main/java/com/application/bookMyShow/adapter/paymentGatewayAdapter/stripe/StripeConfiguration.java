package com.application.bookMyShow.adapter.paymentGatewayAdapter.stripe;

import com.stripe.Stripe;
import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfiguration {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Bean
    public StripeClient getStripeClient(){
        Stripe.apiKey=stripeApiKey;
        //StripeClient.builder().
        //return StripeClient.builder().setApiKey(stripeApiKey).build();
        return new StripeClient(stripeApiKey);
    }
}
