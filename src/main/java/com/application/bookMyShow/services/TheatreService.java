package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidCityException;
import com.application.bookMyShow.Exceptions.InvalidTheatreException;
import com.application.bookMyShow.dtos.theatreDtos.*;
import com.application.bookMyShow.models.City;
import com.application.bookMyShow.models.Theatre;
import com.application.bookMyShow.repositories.CityRepository;
import com.application.bookMyShow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<CreateTheatreResponseDto> addTheatre(CreateTheatreRequestDto request) {
        //1 check city
        Optional<City> city= cityRepository.findById(request.getTheatre().getId());
        if(city.isEmpty()){
            throw new InvalidCityException("Invalid City");
        }
        Theatre theatre=CreateTheatreRequestDto.convertToTheatre(request.getTheatre());
        theatre.setCreated_at(System.currentTimeMillis());
        theatre.setUpdated_at(System.currentTimeMillis());
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
            response.getTheatres().add(GetTheatreResponseDtos.convertToTheatreResponseDto(theatre));
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
}
