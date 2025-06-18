package com.application.bookMyShow.services;

import com.application.bookMyShow.dtos.CityRequestDto;
import com.application.bookMyShow.dtos.CityResponseDto;
import com.application.bookMyShow.models.City;
import com.application.bookMyShow.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<CityResponseDto> addCity(CityRequestDto requestDto) {
        City city=new City();
        city.setName(requestDto.getCityName());
        Long time = System.currentTimeMillis();
        city.setCreated_at(time);
        city.setUpdated_at(time);
        city=cityRepository.save(city);
        CityResponseDto cityResponseDto=new CityResponseDto();
        cityResponseDto.setCity(city);
        cityResponseDto.setMessage("City Added Successfully");
        return new ResponseEntity<>(cityResponseDto, HttpStatus.OK);
    }
}
