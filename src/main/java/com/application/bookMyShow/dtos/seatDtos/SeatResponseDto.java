package com.application.bookMyShow.dtos.seatDtos;

import com.application.bookMyShow.dtos.screenDtos.ScreenResponseDto;
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
public class SeatResponseDto {
    private Long id;
    private String number;
    private SeatType seatType;
//    private ScreenResponseDto screen;

    public static SeatResponseDto convertToSeatResponseDto(Seat seat){
        SeatResponseDto response=new SeatResponseDto();
        response.setId(seat.getId());
        response.setNumber(seat.getNumber());
        response.setSeatType(seat.getSeatType());
//        response.setScreen(ScreenResponseDto.convertToScreenResponseDto(seat.getScreen()));
        return response;
    }
}
