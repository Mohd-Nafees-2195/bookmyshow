package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidTheatreException;
import com.application.bookMyShow.dtos.screenDtos.ScreenRequestDto;
import com.application.bookMyShow.dtos.screenDtos.ScreenResponseDto;
import com.application.bookMyShow.models.Screen;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.Theatre;
import com.application.bookMyShow.repositories.ScreenRepository;
import com.application.bookMyShow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public ResponseEntity<ScreenResponseDto> addScreen(ScreenRequestDto requestDto) {
        Screen screen=new Screen();
        screen.setName(requestDto.getScreenName());
        Optional<Theatre> theatre= theatreRepository.findById(requestDto.getTheatreId());
        if(theatre.isEmpty()){
            throw new InvalidTheatreException("Invalid Theatre");
        }
        screen.setTheatre(theatre.get());
        Long time = System.currentTimeMillis();
        for(Seat seat:requestDto.getSeats()){
            seat.setCreated_at(time);
            seat.setUpdated_at(time);
            seat.setScreen(screen);
        }
        screen.setSeats(requestDto.getSeats());
        screen.setCreated_at(time);
        screen.setUpdated_at(time);

        screenRepository.save(screen);
        ScreenResponseDto responseDto=new ScreenResponseDto();
        responseDto.setScreen(screen);
        responseDto.setMessage("Screen Added Successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
