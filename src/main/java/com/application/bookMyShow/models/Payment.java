package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.PaymentMode;
import com.application.bookMyShow.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {

    private Long amount;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    private String transactionId;

    @ManyToOne
    private Booking booking;
}
