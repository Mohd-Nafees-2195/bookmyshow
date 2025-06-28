package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidCityException;
import com.application.bookMyShow.dtos.theatreDtos.TheatreRequestDto;
import com.application.bookMyShow.dtos.theatreDtos.TheatreResponseDto;
import com.application.bookMyShow.models.City;
import com.application.bookMyShow.models.Theatre;
import com.application.bookMyShow.repositories.CityRepository;
import com.application.bookMyShow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<TheatreResponseDto> addTheatre(TheatreRequestDto requestDto) {
        //1 check city
        Optional<City> city= cityRepository.findById(requestDto.getCityId());
        if(city.isEmpty()){
            throw new InvalidCityException("Invalid City");
        }
        Theatre theatre=new Theatre();
        theatre.setName(requestDto.getTheatreName());
        Long time = System.currentTimeMillis();
        theatre.setCreated_at(time);
        theatre.setUpdated_at(time);
        theatre.setCityId(city.get());
        theatreRepository.save(theatre);
        Optional<Theatre> theatre1=theatreRepository.findById(1L);
        TheatreResponseDto responseDto=new TheatreResponseDto();
        responseDto.setTheatre(theatre);
        responseDto.setMessage("Theatre added successfully!!");
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
