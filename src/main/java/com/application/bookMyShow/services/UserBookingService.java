package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidSeatException;
import com.application.bookMyShow.Exceptions.InvalidUserException;
import com.application.bookMyShow.dtos.userDtos.UserBookingRequestDto;
import com.application.bookMyShow.dtos.userDtos.UserBookingResponseDto;
import com.application.bookMyShow.models.*;
import com.application.bookMyShow.models.enums.BookingStatus;
import com.application.bookMyShow.models.enums.ShowSeatStatus;
import com.application.bookMyShow.repositories.ShowSheetRepository;
import com.application.bookMyShow.repositories.UserBookingRepository;
import com.application.bookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBookingService {
    @Autowired
    private UserBookingRepository userBookingRepository;

    @Autowired
    private ShowSheetRepository showSheetRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<UserBookingResponseDto> bookTickets(UserBookingRequestDto requestDto) throws InvalidUserException {
        //Validate User
        Optional<User> savedUser=userRepository.findById(requestDto.getUserId());
        if(savedUser.isEmpty()){
            throw new InvalidUserException("Invalid User");
        }
        //Validate sheets
        List<ShowSheet> showSheets=showSheetRepository.findAllById(requestDto.getShowSheetIds());
        Long totalPrice= 0L;
        for(ShowSheet showSheet:showSheets){
            if(showSheet.getShowSheetStatus()!=ShowSeatStatus.AVAILABLE){
                throw new InvalidSeatException("Seat has already been booked");
            }
            showSheet.setShowSheetStatus(ShowSeatStatus.BOOKED);
            totalPrice+=showSheet.getPrice();//total price
        }
        Show show=showSheets.get(0).getShow();
        Booking booking=new Booking();
        booking.setShowSeats(showSheets);
        String bookingNumber=requestDto.getUserId()+"_"+show.getId()+"_"+Math.random();
        booking.setBookingNumber(bookingNumber);
        booking.setAmount(totalPrice);

        List<Payment> payments=new ArrayList<>();
//        Payment payment=new Payment();
//        payment.setAmount(price);
//        if(requestDto.getPaymentMode()== PaymentMode.UPI){
//            payment.setPaymentMode(PaymentMode.UPI);
//        }
//        //DO payment
//        payment.setPaymentStatus(PaymentStatus.PAID);
//        payment.setTransactionId("As of now"+Math.random());
//        payments.add(payment);
        booking.setPayments(payments);
       // if(payment.getPaymentStatus()==PaymentStatus.PAID){
        showSheetRepository.saveAll(showSheets);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        Long time=System.currentTimeMillis();
        booking.setCreated_at(time);
        booking.setUpdated_at(time);
        booking.setUser(savedUser.get());
        userBookingRepository.save(booking);
    //    }
        UserBookingResponseDto responseDto=new UserBookingResponseDto();
        responseDto.setBooking(booking);
        return new  ResponseEntity<>(responseDto,HttpStatus.OK );
    }
}
