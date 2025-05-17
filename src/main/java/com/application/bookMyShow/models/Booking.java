package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private String bookingNumber;

    @ManyToMany
    private List<ShowSheet> showSeats;
    private Long amount;

    @OneToMany
    private List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    @ManyToOne
    private User user;
}

/*
Booking --> ShowSeat
1 --> M
M <-- 1
 */

/*
Booking --> Payment
1 --> M
1 <-- 1
 */

/*
Booking --> User
1 --> 1
M <-- 1
 */