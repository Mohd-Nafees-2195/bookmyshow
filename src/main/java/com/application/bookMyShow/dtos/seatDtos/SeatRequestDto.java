package com.application.bookMyShow.dtos.seatDtos;

import com.application.bookMyShow.dtos.screenDtos.ScreenRequestDto;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class SeatRequestDto {
    private Long screenId;
    private String number;
    private SeatType seatType;
//    private ScreenRequestDto screen;

    public static Seat convertToSeat(SeatRequestDto request){
        Seat seat=new Seat();
        seat.setNumber(request.getNumber());
        seat.setSeatType(request.getSeatType());
        return seat;
    }
//    private Seat seat;
}
