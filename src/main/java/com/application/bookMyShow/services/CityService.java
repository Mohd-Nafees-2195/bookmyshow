package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidCityException;
import com.application.bookMyShow.dtos.cityDtos.*;
import com.application.bookMyShow.models.City;
import com.application.bookMyShow.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<CreateCityResponseDto> addCity(CityRequestDto request) {
        City city=CityRequestDto.convertToCity(request);
        city.setCreated_at(new Date());
        city.setUpdated_at(new Date());
        city.setIsDeleted(false);
        city=cityRepository.save(city);
        CreateCityResponseDto response=new CreateCityResponseDto();
        response.setCity(CityResponseDto.convertToCityResponseDto(city));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GetCityResponseDto> getCity(Long id) {
        Optional<City> city=cityRepository.findById(id);
        if(city.isEmpty()){
            throw new InvalidCityException("City Not Found");
        }
        GetCityResponseDto response=new GetCityResponseDto();
        response.setCity(CityResponseDto.convertToCityResponseDto(city.get()));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<CityResponseDtos> getAllCity() {
        List<City> cities=cityRepository.findAll();
        if(cities.isEmpty()){
            throw new InvalidCityException("City Not Found");
        }
        CityResponseDtos response=new CityResponseDtos();
        response.setCities(new ArrayList<>());
        cities.forEach(city -> response.getCities().add(CityResponseDto.convertToCityResponseDto(city)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
