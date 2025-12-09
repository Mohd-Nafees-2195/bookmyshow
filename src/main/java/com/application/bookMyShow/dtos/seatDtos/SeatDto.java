package com.application.bookMyShow.dtos.seatDtos;

import com.application.bookMyShow.dtos.screenDtos.ScreenRequestDto;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.enums.SeatType;
import lombok.Data;

@Data
public class SeatDto {
    private Long screenId;
    private String number;
    private SeatType seatType;

    public static SeatDto convertToSearDto(Seat seat){
        SeatDto response=new SeatDto();
        response.setNumber(seat.getNumber());
        response.setSeatType(seat.getSeatType());
        return response;
    }
}
