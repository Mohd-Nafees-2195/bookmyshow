package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.*;
import com.application.bookMyShow.dtos.*;
import com.application.bookMyShow.models.*;
import com.application.bookMyShow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private MovieLanguageRepository movieLanguageRepository;
    @Autowired
    private ShowSheetRepository showSheetRepository;

    public ResponseEntity<ShowResponseDto> addShow(ShowRequestDto requestDto) {
        //Check the show timing availability in the selected screen.
        List<Show> shows=showRepository.findAllByScreenId(requestDto.getScreenId(),requestDto.getStartTime(),requestDto.getEndTime());
        //if show is not conflicting then create the show else throw the exception slot not available
        if(!shows.isEmpty()){
            throw new InvalidSlotException("Timing slot is not available");
        }
        //Fetch the movie
        Optional<Movie> movie=movieRepository.findById(requestDto.getMovieId());
        if(movie.isEmpty()){
            throw new InvalidMovieException("Invalid movie selectin");
        }
        //Fetch the screen
        Optional<Screen> screen=screenRepository.findById(requestDto.getScreenId());
        if(screen.isEmpty()){
            throw new InvalidScreenException("Invalid screen selection");
        }
        //Fetch Language
//        MovieLanguageDto movieLanguageDto=requestDto.getMovieLanguageDto();
//        Optional<Language> language=languageRepository.findById(movieLanguageDto.getLanguageId());
//        if(language.isEmpty()){
//            throw new InvalidLanguageException("Invalid Language");
//        }
//        //Add movie_language first
//        MovieLanguage movieLanguage=new MovieLanguage();
//        movieLanguage.setLanguage(language.get());
//        movieLanguage.setMovie(movie.get());
//        movieLanguage.setMovieType(movieLanguageDto.getMovieType());
//        movieLanguage.setSubtitle(movieLanguageDto.getIsSubtitle());
//        movieLanguage.setAudio(movieLanguageDto.getIsAudio());
//        movieLanguageRepository.save(movieLanguage);


        Show newShow=new Show();
        newShow.setScreen(screen.get());
        newShow.setMovie(movie.get());
        newShow.setFeatures(requestDto.getFeatures());
        newShow.setStartTime(requestDto.getStartTime());
        newShow.setEndTime(requestDto.getEndTime());
        Long time=System.currentTimeMillis();
        newShow.setCreated_at(time);
        newShow.setUpdated_at(time);
        showRepository.save(newShow);

        //Now Add record into show_sheet
        List<ShowSheetDto> showSheetDtos=requestDto.getShowSheetDtos();
        List<ShowSheet> showSheets=new ArrayList<>();
        List<Seat> seats=screen.get().getSeats();
        int i=0;
        if(seats.size()!=showSheetDtos.size()){
            throw new InvalidSeatException("Seat is missing");
        }
        for(ShowSheetDto seat:showSheetDtos){
            ShowSheet showSheet=new ShowSheet();
            showSheet.setShow(newShow);
            showSheet.setSeat(seats.get(i++));
            showSheet.setPrice(seat.getPrice());
            showSheet.setShowSheetStatus(seat.getShowSheetStatus());
            showSheet.setCreated_at(time);
            showSheet.setUpdated_at(time);
            showSheets.add(showSheet);
        }
        showSheetRepository.saveAll(showSheets);
        ShowResponseDto responseDto=new ShowResponseDto();
        responseDto.setShow(newShow);
        responseDto.setMessage("Show Added Successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
