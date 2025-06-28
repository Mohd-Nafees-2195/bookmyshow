package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidScreenException;
import com.application.bookMyShow.dtos.seatDtos.SeatRequestDto;
import com.application.bookMyShow.dtos.seatDtos.SeatResponseDto;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.repositories.ScreenRepository;
import com.application.bookMyShow.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    public ResponseEntity<SeatResponseDto> addSeat(SeatRequestDto requestDto) {
        Optional<Screen> screen=screenRepository.findById(requestDto.getScreenId());
        if(screen.isEmpty()){
            throw new InvalidScreenException("Invalid Screen Exception");
        }
        Seat seat= requestDto.getSeat();
        Long time=System.currentTimeMillis();
        seat.setCreated_at(time);
        seat.setUpdated_at(time);
        seat.setScreen(screen.get());
        seatRepository.save(seat);
        SeatResponseDto responseDto=new SeatResponseDto();
        responseDto.setSeat(seat);
        responseDto.setMessage("Seat Added Successfully!!");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
