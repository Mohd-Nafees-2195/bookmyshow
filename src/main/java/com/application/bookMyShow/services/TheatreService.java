package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidCityException;
import com.application.bookMyShow.Exceptions.InvalidTheatreException;
import com.application.bookMyShow.dtos.screenDtos.ScreenInfoDto;
import com.application.bookMyShow.dtos.showDtos.ShowResponseDto;
import com.application.bookMyShow.dtos.showDtos.ShowResponseDtos;
import com.application.bookMyShow.dtos.theatreDtos.*;
import com.application.bookMyShow.models.*;
import com.application.bookMyShow.models.enums.Role;
import com.application.bookMyShow.models.enums.ShowStatus;
import com.application.bookMyShow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public ResponseEntity<CreateTheatreResponseDto> addTheatre(CreateTheatreRequestDto request) {
        //1 check city
        Optional<City> city= cityRepository.findById(request.getTheatre().getCityId());
        if(city.isEmpty()){
            throw new InvalidCityException("Invalid City");
        }
        Theatre theatre=CreateTheatreRequestDto.convertToTheatre(request.getTheatre());
        theatre.setCreated_at(new Date());
        theatre.setUpdated_at(new Date());
        theatre.setCityId(city.get());
        theatre=theatreRepository.save(theatre);
        CreateTheatreResponseDto response=new CreateTheatreResponseDto();
        response.setTheatre(CreateTheatreResponseDto.convertToTheatreResponseDto(theatre));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<GetTheatreResponseDto> getTheatre(Long id) {
        Optional<Theatre> theatre=theatreRepository.findById(id);
        GetTheatreResponseDto response=new GetTheatreResponseDto();
        if(theatre.isPresent()){
            response.setTheatre(GetTheatreResponseDto.convertToTheatreResponseDto(theatre.get()));
            return new  ResponseEntity<>(response,HttpStatus.OK);
        }
        return new  ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<GetTheatreResponseDtos> getAllTheatre() {
        List<Theatre> theatres=theatreRepository.findAll();
        GetTheatreResponseDtos response=new GetTheatreResponseDtos();
        response.setTheatres(new ArrayList<>());
        theatres.forEach((theatre -> {
            response.getTheatres().add(TheatreResponseDto.convertToTheatreResponseDto(theatre));
        }));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTheatre(Long id) {
        try {
            theatreRepository.deleteById(id);
            return new ResponseEntity<>("Theatre Deleted Successfully!!",HttpStatus.OK);
        }catch (Exception e){
            throw new InvalidTheatreException("Something went wrong!Please Try Again Later");
        }
    }

    public ResponseEntity<TheaterDataDto> getTheaterDataByOwnerId(Long id) {
        if(id==null || id <=0) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        //1 Validate user
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty() || user.get().getRole()!= Role.THEATRE_OWNER){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        TheaterDataDto response=new TheaterDataDto();

        //2 Fetch Theater by user id

        List<Theatre> theatres=theatreRepository.findByUserId(id);


        //3 Fetch the active show from movie_show table by screenIds, take screenIds from above theaters
        List<Long> screenIds=new ArrayList<>();
//        List<Long> theaterIds=new ArrayList<>();
        List<TheatreInfoDto> theaterResponse=new ArrayList<>();

        for(Theatre theatre:theatres){
            TheatreInfoDto theatreInfoDto=new TheatreInfoDto(theatre.getId(),theatre.getName());
            theaterResponse.add(theatreInfoDto);
//            List<ScreenInfoDto> screenResponse=new ArrayList<>();
            for(Screen screen:theatre.getScreens()){
                screenIds.add(screen.getId());
                theatreInfoDto.getScreens().add(new ScreenInfoDto(screen.getId(),screen.getName()));
            }
        }

        List<Show> shows=showRepository.findByScreenIdInAndShowStatus(screenIds, ShowStatus.ACTIVE);

        List<Long> showIds=new ArrayList<>();

        ShowResponseDtos showsResponse=new ShowResponseDtos();
        showsResponse.setShows(new ArrayList<>());
        for(Show show:shows){
            showIds.add(show.getId());
            showsResponse.getShows().add(ShowResponseDto.convertToShowResponseDto(show));
        }
        response.setShows(showsResponse); //add all active shows to response

        //4 Fetch the bookings to calculate total revenue
        List<Booking> bookings=bookingRepository.findByShowIdIn(showIds);
        response.setTotalBooking((long) bookings.size());
        response.setActiveShows((long)shows.size());
        response.setTotalRevenue(0L);
        for(Booking booking:bookings){
           response.setTotalRevenue(response.getTotalRevenue()+booking.getAmount());
        }
        response.setTheatres(theaterResponse);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
