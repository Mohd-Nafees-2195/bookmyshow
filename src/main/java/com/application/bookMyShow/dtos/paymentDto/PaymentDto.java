package com.application.bookMyShow.dtos.paymentDto;

import com.application.bookMyShow.models.Payment;
import com.application.bookMyShow.models.enums.PaymentMode;
import com.application.bookMyShow.models.enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDto {
    private Long id;
    private Long amount;

    private PaymentMode paymentMode;

    private PaymentStatus paymentStatus;
    private String transactionId;

    public static PaymentDto convertToPaymentDto(Payment payment){
        PaymentDto response=new PaymentDto();
        response.setId(payment.getId());
        response.setAmount(payment.getAmount());
        response.setPaymentMode(payment.getPaymentMode());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setTransactionId(payment.getTransactionId());
        return  response;
    }
}
