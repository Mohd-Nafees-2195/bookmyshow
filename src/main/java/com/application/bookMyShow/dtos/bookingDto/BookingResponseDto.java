package com.application.bookMyShow.dtos.bookingDto;

import com.application.bookMyShow.dtos.paymentDto.PaymentDto;
import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetDto;
import com.application.bookMyShow.models.Booking;
import com.application.bookMyShow.models.User;
import com.application.bookMyShow.models.enums.BookingStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingResponseDto {
//    private Booking booking;
    private Long id;
    private String bookingNumber;
    private List<ShowSheetDto> showSeats;
    private Long amount;

    private List<PaymentDto> payments;
    private BookingStatus bookingStatus;
    private User user;

    public static BookingResponseDto convertToUserBookingResponseDto(Booking booking){
        BookingResponseDto response=new BookingResponseDto();
        response.setId(booking.getId());
        response.setBookingNumber(booking.getBookingNumber());
        response.setShowSeats(new ArrayList<>());
        response.setAmount(booking.getAmount());
        booking.getShowSeats().forEach(showSheet -> response.getShowSeats().add(ShowSheetDto.convertTo(showSheet)));
        response.setPayments(new ArrayList<>());
        booking.getPayments().forEach(payment -> response.getPayments().add(PaymentDto.convertToPaymentDto(payment)));
        response.setBookingStatus(booking.getBookingStatus());
        return response;
    }
}
