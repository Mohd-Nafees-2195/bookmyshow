package com.application.bookMyShow.adapter.paymentGatewayAdapter;

import com.application.bookMyShow.adapter.paymentGatewayAdapter.stripe.StripePaymentGatewayAdapter;
import com.application.bookMyShow.models.enums.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayStrategy {
    private PaymentGatewayAdapter paymentGatewayAdapter;

    public PaymentGatewayStrategy(PaymentGatewayAdapter paymentGatewayAdapter){
        this.paymentGatewayAdapter=paymentGatewayAdapter;
    }

    public static PaymentGatewayAdapter getPaymentGatewayAdapter(PaymentGateway paymentGateway){
        if(paymentGateway.equals(PaymentGateway.STRIPE)){
            PaymentGatewayStrategy paymentGatewayStrategy=new PaymentGatewayStrategy(new StripePaymentGatewayAdapter());
            return paymentGatewayStrategy.paymentGatewayAdapter;
        }
        return null;
    }
}
