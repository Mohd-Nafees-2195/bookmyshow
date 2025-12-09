package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidScreenException;
import com.application.bookMyShow.Exceptions.InvalidTheatreException;
import com.application.bookMyShow.dtos.screenDtos.*;
import com.application.bookMyShow.dtos.seatDtos.SeatRequestDto;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.Theatre;
import com.application.bookMyShow.repositories.ScreenRepository;
import com.application.bookMyShow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public ResponseEntity<CreateScreenResponseDto> addScreen(ScreenRequestDto request) {
        Screen screen=ScreenRequestDto.convertToScreen(request);
        Optional<Theatre> theatre= theatreRepository.findById(request.getTheatreId());
        if(theatre.isEmpty()){
            throw new InvalidTheatreException("Invalid Theatre");
        }
        screen.setTheatre(theatre.get());

        List<Seat> seats=new ArrayList<>();
        for(SeatRequestDto seatDto:request.getSeats()){
            Seat seat=SeatRequestDto.convertToSeat(seatDto);
            seat.setCreated_at(new Date());
            seat.setUpdated_at(new Date());
            seat.setScreen(screen);
            seats.add(seat);
        }
        screen.setSeats(seats);
        screen.setCreated_at(new Date());
        screen.setUpdated_at(new Date());
        screen.setFeatures(request.getFeatures());
        screen=screenRepository.save(screen);
        CreateScreenResponseDto response=new CreateScreenResponseDto();
        response.setScreen(ScreenResponseDto.convertToScreenResponseDto(screen));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GetScreenResponseDto> getScreen(Long id) {
        Optional<Screen> screen=screenRepository.findById(id);
        if(screen.isEmpty()){
            throw new InvalidScreenException("Screen not found");
        }
        GetScreenResponseDto response=new GetScreenResponseDto();
        response.setScreen(ScreenResponseDto.convertToScreenResponseDto(screen.get()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ScreenResponseDtos> getAllScreen() {
        List<Screen> screens=screenRepository.findAll();
        if(screens.isEmpty()){
            throw new InvalidScreenException("No screen found");
        }
        ScreenResponseDtos response=new ScreenResponseDtos();
        response.setScreens(new ArrayList<>());
        screens.forEach(screen -> response.getScreens().add(ScreenResponseDto.convertToScreenResponseDto(screen)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
