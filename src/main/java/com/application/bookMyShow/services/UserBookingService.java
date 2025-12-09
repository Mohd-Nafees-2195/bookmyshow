package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidSeatException;
import com.application.bookMyShow.Exceptions.InvalidUserException;
import com.application.bookMyShow.dtos.bookingDto.BookingRequestDto;
import com.application.bookMyShow.dtos.bookingDto.BookingResponseDto;
import com.application.bookMyShow.models.*;
import com.application.bookMyShow.models.enums.BookingStatus;
import com.application.bookMyShow.models.enums.ShowSeatStatus;
import com.application.bookMyShow.repositories.ShowSheetRepository;
import com.application.bookMyShow.repositories.BookingRepository;
import com.application.bookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserBookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowSheetRepository showSheetRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<BookingResponseDto> bookTickets(BookingRequestDto requestDto) throws InvalidUserException {
        //Validate User
        Optional<User> savedUser=userRepository.findById(requestDto.getUserId());
        if(savedUser.isEmpty()){
            throw new InvalidUserException("Invalid User");
        }
        //Validate sheets
        List<ShowSheet> showSheets=showSheetRepository.findAllById(requestDto.getShowSheetIds());
        Long totalAmount= 0L;
        for(ShowSheet showSheet:showSheets){
            if(showSheet.getShowSheetStatus()!=ShowSeatStatus.AVAILABLE){
                throw new InvalidSeatException("Seat has already been booked");
            }
            showSheet.setShowSheetStatus(ShowSeatStatus.BLOCKED);
            totalAmount+=showSheet.getPrice();//total price
        }
        Show show=showSheets.get(0).getShow();
        Booking booking=new Booking();
        booking.setShowSeats(showSheets);
        String bookingNumber=requestDto.getUserId()+"_"+show.getId()+"_"+Math.random();
        booking.setBookingNumber(bookingNumber);
        booking.setAmount(totalAmount);

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
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreated_at(new Date());
        booking.setUpdated_at(new Date());
        booking.setUser(savedUser.get());
        booking= bookingRepository.save(booking);
    //    }
        BookingResponseDto responseDto= BookingResponseDto.convertToUserBookingResponseDto(booking);
        return new  ResponseEntity<>(responseDto,HttpStatus.OK );
    }
}
