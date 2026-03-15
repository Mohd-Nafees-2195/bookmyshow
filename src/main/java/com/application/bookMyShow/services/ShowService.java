package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.*;
import com.application.bookMyShow.dtos.ShowTimingDtos.ShowTimingRequestDto;
import com.application.bookMyShow.dtos.showDtos.*;
import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetDto;
import com.application.bookMyShow.models.*;
import com.application.bookMyShow.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ShowSheetRepository showSheetRepository;

//    @Autowired
//    private TheatreRepository theatreRepository;

    @Transactional
    public ResponseEntity<CreateShowResponseDto> addShow(CreateShowRequestDto request) {

        //Fetch the movie
        Optional<Movie> movie=movieRepository.findById(request.getShow().getMovieId());
        if(movie.isEmpty()){
            throw new InvalidMovieException("Invalid movie selection");
        }
        //Fetch the screen
        Optional<Screen> screen=screenRepository.findById(request.getShow().getScreenId());
        if(screen.isEmpty()){
            throw new InvalidScreenException("Invalid screen selection");
        }

        //Fetch Theatre
        List<Show> shows=showRepository.findByScreen_Theatre_Id(request.getShow().getTheatreId());
        //Check the show timing availability in the selected screen.
        //if show is not conflicting then create the show else throw the exception slot not available
        if(!shows.isEmpty()){
            long bufferMillis = 15 * 60 * 1000; // 15 minutes in milliseconds
            for (Show show : shows) {
                if(Objects.equals(request.getShow().getScreenId(), show.getScreen().getId())){
//                    for (ShowTiming existingTiming : show.getShowTimings()) {
                        for (ShowTimingRequestDto newTiming : request.getShow().getShowTimings()) {

                            Date newStart = newTiming.getStartTime();
                            Date newEnd = newTiming.getEndTime();
                            Date existingStart = show.getStartTime();
                            Date existingEnd = show.getEndTime();

                            // Apply buffer to existing start and end
                            Date existingStartWithBuffer = new Date(existingStart.getTime() - bufferMillis);
                            Date existingEndWithBuffer = new Date(existingEnd.getTime() + bufferMillis);

                            // Check for conflict
                            if (newStart.before(existingEndWithBuffer) && newEnd.after(existingStartWithBuffer)) {
                                throw new InvalidSlotException("Conflict with existing show! "+newStart+" to "+newEnd);
                                // Handle conflict (return error or skip)
                            }
                        }
                }else{
                    //Check only for date
                    long bufferMinutes=14L;
//                    for (ShowTiming existingTiming : show.getShowTimings()) {
                        for (ShowTimingRequestDto newTiming : request.getShow().getShowTimings()) {
                            LocalDate newLocalStartDate=toLocalDate(newTiming.getStartTime());
                            LocalDate newLocalEndDate=toLocalDate(newTiming.getEndTime());
                            LocalDate existLocalStartDate=toLocalDate(show.getStartTime());
                            LocalDate existLocalEndDate=toLocalDate(show.getEndTime());

                            if(newLocalStartDate.equals(existLocalStartDate)&&newLocalEndDate.equals(existLocalEndDate)){
                                LocalTime newStart = toLocalTimeHM(newTiming.getStartTime());
                                LocalTime newEnd   = toLocalTimeHM(newTiming.getEndTime());
                                LocalTime existingStart = toLocalTimeHM(show.getStartTime());
                                //LocalTime existingEnd   = toLocalTimeHM(existingTiming.getEndTime());

                                // Apply buffer in minutes
                                LocalTime existingStartWithBuffer = existingStart.minusMinutes(bufferMinutes);
                                LocalTime existingEndWithBuffer   = existingStart.plusMinutes(bufferMinutes);

                                System.out.println(existingStartWithBuffer+" - "+existingEndWithBuffer);

                                // Conflict check
                                boolean overlaps = newStart.isBefore(existingEndWithBuffer)
                                        && newStart.isAfter(existingStartWithBuffer);

                                if (overlaps) {
                                    throw new InvalidSlotException(
                                            "Conflict with existing show! " + newStart + " to " + newEnd
                                    );
                                }
                            }
                        }
                }
            }
        }

        System.out.println("ajksjkndklmd");
        List<ShowSheetDto> showSheetDtos=request.getShow().getShowSheets();
        List<Seat> seats=screen.get().getSeats();
        if(seats.size()!=showSheetDtos.size()){
            throw new InvalidSeatException("Seat is missing");
        }
        CreateShowResponseDto response=new CreateShowResponseDto();
        response.setShows(new ArrayList<>());
        for(ShowTimingRequestDto timingDto:request.getShow().getShowTimings()){
            Show newShow= ShowRequestDto.convertToShow(request.getShow());
            newShow.setScreen(screen.get());
            newShow.setMovie(movie.get());
            newShow.setCreated_at(new Date());
            newShow.setUpdated_at(new Date());
            newShow.setIsDeleted(false);
            newShow.setStartTime(timingDto.getStartTime());
            newShow.setEndTime(timingDto.getEndTime());
            newShow=showRepository.save(newShow);

            //Now Add record into show_sheet
            List<ShowSheet> showSheets=new ArrayList<>();
            int i=0;
            for(ShowSheetDto seat:showSheetDtos){
                ShowSheet showSheet=new ShowSheet();
                showSheet.setShow(newShow);
                showSheet.setSeat(seats.get(i++));
                showSheet.setPrice(seat.getPrice());
                showSheet.setShowSheetStatus(seat.getShowSheetStatus());
                showSheet.setCreated_at(new Date());
                showSheet.setUpdated_at(new Date());
                showSheets.add(showSheet);
            }
            showSheets=showSheetRepository.saveAll(showSheets);
            newShow.setShowSheets(showSheets);
            ShowResponseDto showResponse=ShowResponseDto.convertToShowResponseDto(newShow);
            for(int j=0;j<showSheets.size();j++){
                showSheetDtos.get(j).setSeatId(showSheets.get(j).getId());
            }
            showResponse.setShowSheets(showSheetDtos);
            response.getShows().add(showResponse);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GetShowResponseDto> getShow(Long id) {
        Optional<Show> show=showRepository.findById(id);
        if(show.isEmpty()){
            throw new InvalidShowException("Invalid Show!! Show Not Found");
        }
        GetShowResponseDto response=new GetShowResponseDto();
        response.setShow(CreateShowResponseDto.convertToShowResponseDto(show.get()));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ShowResponseDtos> getAllShow() {
        List<Show> shows=showRepository.findAll();
        if(shows.isEmpty()){
            throw new InvalidShowException("Show Not Available");
        }
        ShowResponseDtos response=new ShowResponseDtos();
        response.setShows(new ArrayList<>());
        shows.forEach(show -> response.getShows().add(ShowResponseDto.convertToShowResponseDto(show)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ShowResponseDtos> getAllShowByMovieIdAndDate(Long movieId,String date) {
        List<Show> shows=showRepository.findByMovieIdAndIsDeleted(movieId,false);
        if(shows.isEmpty()){
            throw new InvalidShowException("Show Not Available");
        }
        List<ShowResponseDto> showsList=new ArrayList<>();
        ShowResponseDtos response=new ShowResponseDtos();
        response.setShows(showsList);
        shows.forEach(show -> response.getShows().add(ShowResponseDto.convertToShowResponseDto(show)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ShowResponseDtos> getAllShowByMovieId(Long movieId) {
        List<Show> shows=showRepository.findByMovieIdAndIsDeleted(movieId,false);
        if(shows.isEmpty()){
            throw new InvalidShowException("Show Not Available");
        }
        ShowResponseDtos response=new ShowResponseDtos();
        response.setShows(new ArrayList<>());
        shows.forEach(show -> response.getShows().add(ShowResponseDto.convertToShowResponseDto(show)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ShowResponseDtos> getAllShowByTheatreIds(List<Long> theatreIds) {
        List<Show> shows=showRepository.findAllByScreen_Theatre_IdIn(theatreIds);
        if(shows.isEmpty()){
            throw new InvalidShowException("No show found!!");
        }
        ShowResponseDtos response=new ShowResponseDtos();
        response.setShows(new ArrayList<>());
        shows.forEach(show -> response.getShows().add(ShowResponseDto.convertToShowResponseDto(show)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private LocalTime toLocalTimeHM(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault()) // or ZoneId.of("UTC")
                .toLocalTime()
                .truncatedTo(ChronoUnit.MINUTES); // drop seconds & millis
    }
    private LocalDate toLocalDate(Date date){
        // or ZoneId.of("UTC")
        return date.toInstant()
                .atZone(ZoneId.systemDefault()) // or ZoneId.of("UTC")
                .toLocalDate();
    }

}
