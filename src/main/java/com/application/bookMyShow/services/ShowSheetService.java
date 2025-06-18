package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidSeatException;
import com.application.bookMyShow.Exceptions.InvalidShowException;
import com.application.bookMyShow.dtos.ShowSheetRequestDto;
import com.application.bookMyShow.dtos.ShowSheetResponseDto;
import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.repositories.SeatRepository;
import com.application.bookMyShow.repositories.ShowRepository;
import com.application.bookMyShow.repositories.ShowSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowSheetService {
    @Autowired
    private ShowSheetRepository showSheetRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private SeatRepository seatRepository;

    public ResponseEntity<ShowSheetResponseDto> addShowSheet(ShowSheetRequestDto requestDto) {
        //Here adding show sheet separately does not make any sense because
        //We are adding show sheet at the time of adding show

        //check the show
        Optional<Show> show=showRepository.findById(requestDto.getShowId());
        if(show.isEmpty()){
            throw new InvalidShowException("Invalid SHow");
        }
        //check the sheat
        Optional<Seat> seat=seatRepository.findById(requestDto.getSeatId());
        if(seat.isEmpty()){
            throw new InvalidSeatException("Invalid Seat");
        }
        ShowSheet showSheet=new ShowSheet();
        showSheet.setShow(show.get());
        showSheet.setSeat(seat.get());
        showSheet.setPrice(requestDto.getPrice());
        showSheet.setShowSheetStatus(requestDto.getShowSheetStatus());
        Long time=System.currentTimeMillis();
        showSheet.setCreated_at(time);
        showSheet.setUpdated_at(time);
        showSheetRepository.save(showSheet);
        ShowSheetResponseDto responseDto=new ShowSheetResponseDto();
        responseDto.setShowSheet(showSheet);
        responseDto.setMessage("Show Sheet added successfully!!");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
