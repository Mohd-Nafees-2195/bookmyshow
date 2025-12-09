package com.application.bookMyShow.dtos.cityDtos;

import com.application.bookMyShow.models.City;
import lombok.Data;

@Data
public class CityRequestDto {
    private String name;

    public static City convertToCity(CityRequestDto request){
        City newCity=new City();
        newCity.setName(request.getName());
        return newCity;
    }
}
