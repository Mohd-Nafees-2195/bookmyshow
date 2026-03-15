package com.application.bookMyShow.adapter.paymentGatewayAdapter;

public interface PaymentGatewayAdapter {
    String createPaymentLink(Long price,Long bookingId,Long userId) throws Exception;
}
