package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.cityDtos.*;
import com.application.bookMyShow.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<CreateCityResponseDto> addCity(@RequestBody CreateCityRequestDto request){
        return cityService.addCity(request.getCity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCityResponseDto> getCity(@PathVariable Long id){
        return cityService.getCity(id);
    }

    @GetMapping
    public ResponseEntity<CityResponseDtos> getAllCity(){
        return cityService.getAllCity();
    }
}
