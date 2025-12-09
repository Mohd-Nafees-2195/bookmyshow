package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidScreenException;
import com.application.bookMyShow.dtos.seatDtos.*;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.repositories.ScreenRepository;
import com.application.bookMyShow.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    public ResponseEntity<CreateSeatResponseDto> addSeat(SeatRequestDto requestDto) {
        Optional<Screen> screen=screenRepository.findById(requestDto.getScreenId());
        if(screen.isEmpty()){
            throw new InvalidScreenException("Invalid Screen Exception");
        }
        Seat seat= SeatRequestDto.convertToSeat(requestDto);
        seat.setCreated_at(new Date());
        seat.setUpdated_at(new Date());
        seat.setScreen(screen.get());
        seat=seatRepository.save(seat);
        CreateSeatResponseDto response=new CreateSeatResponseDto();
        response.setSeat(SeatResponseDto.convertToSeatResponseDto(seat));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GetSeatResponseDto> getSeat(Long id) {
        Optional<Seat> seat=seatRepository.findById(id);
        if(seat.isEmpty()){
            throw new InvalidScreenException("Invalid Seat Exception");
        }
        GetSeatResponseDto response=new GetSeatResponseDto();
        response.setSeat(SeatResponseDto.convertToSeatResponseDto(seat.get()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SeatResponseDtos> getAllSeat() {
        List<Seat> seats=seatRepository.findAll();
        if(seats.isEmpty()){
            throw new InvalidScreenException("No Seat Found");
        }
        SeatResponseDtos response=new SeatResponseDtos();
        response.setSeats(new ArrayList<>());
        seats.forEach(seat -> response.getSeats().add(SeatResponseDto.convertToSeatResponseDto(seat)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Boolean deleteSeat(Long id) {
        try{
            seatRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
