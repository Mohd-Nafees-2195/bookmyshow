package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidSeatException;
import com.application.bookMyShow.Exceptions.InvalidShowException;
import com.application.bookMyShow.Exceptions.InvalidShowSheetException;
import com.application.bookMyShow.dtos.showSheetDtos.*;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowSheetService {
    @Autowired
    private ShowSheetRepository showSheetRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private SeatRepository seatRepository;

    public ResponseEntity<CreateShowSheetResponseDto> addShowSheet(CreateShowSheetRequestDto requestDto) {
        //Here adding show sheet separately does not make any sense because
        //We are adding show sheet at the time of adding show

        //check the show
        Optional<Show> show=showRepository.findById(requestDto.getShowSheet().getShowId());
        if(show.isEmpty()){
            throw new InvalidShowException("Invalid Show");
        }
        //check the sheat
        Optional<Seat> seat=seatRepository.findById(requestDto.getShowSheet().getSeatId());
        if(seat.isEmpty()){
            throw new InvalidSeatException("Invalid Seat");
        }
        ShowSheet showSheet=ShowSheetRequestDto.convertToShowSheet(requestDto.getShowSheet());
        showSheet.setShow(show.get());
        showSheet.setSeat(seat.get());
        showSheet.setCreated_at(new Date());
        showSheet.setUpdated_at(new Date());
        showSheet=showSheetRepository.save(showSheet);
        CreateShowSheetResponseDto response=new CreateShowSheetResponseDto();
        response.setResponse(CreateShowSheetResponseDto.convertToShowSheetResponseDto(showSheet));
        response.getResponse().setShowId(show.get().getId());
        response.getResponse().setSeatId(seat.get().getId());
        response.getResponse().setMessage("Show Sheet added successfully!!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GetShowSheetResponseDto> getShowSheet(Long id) {
        Optional<ShowSheet> showSheet=showSheetRepository.findById(id);
        if(showSheet.isEmpty()){
            throw new InvalidShowSheetException("Invalid Show Sheet");
        }
        GetShowSheetResponseDto response=new GetShowSheetResponseDto();
        response.setResponse(GetShowSheetResponseDto.convertToShowSheetResponseDto(showSheet.get()));
        response.getResponse().setShowId(showSheet.get().getShow().getId());
        response.getResponse().setSeatId(showSheet.get().getSeat().getId());
        response.getResponse().setMessage("Show Sheet fetched successfully!!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<GetShowSheetResponseDtos> getAllShowSheet() {
        List<ShowSheet> showSheets=showSheetRepository.findAll();
        GetShowSheetResponseDtos response=new GetShowSheetResponseDtos();
        response.setShowSheets(new ArrayList<>());
        showSheets.forEach((showSheet -> {
            ShowSheetResponseDto sheetResponseDto=GetShowSheetResponseDtos.convertToShowSheetResponseDto(showSheet);
            sheetResponseDto.setShowId(showSheet.getShow().getId());
            sheetResponseDto.setSeatId(showSheet.getSeat().getId());
            response.getShowSheets().add(sheetResponseDto);
        }));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
