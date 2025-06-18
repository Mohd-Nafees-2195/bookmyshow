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
    @JoinTable(
            name = "booking_showsheet", // Name of the join table
            joinColumns = @JoinColumn(name = "booking_id"), // Foreign key in join table for Student
            inverseJoinColumns = @JoinColumn(name = "showsheet_id") // Foreign key in join table for Course
    )
    private List<ShowSheet> showSeats;
    private Long amount;

    @OneToMany
    @JoinColumn(name = "booking_id")
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