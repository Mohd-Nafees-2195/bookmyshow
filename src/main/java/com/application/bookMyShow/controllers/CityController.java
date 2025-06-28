package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.cityDtos.CityRequestDto;
import com.application.bookMyShow.dtos.cityDtos.CityResponseDto;
import com.application.bookMyShow.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("citys")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping()
    public ResponseEntity<CityResponseDto> addCity(@RequestBody CityRequestDto requestDto){
        return cityService.addCity(requestDto);
    }
}
