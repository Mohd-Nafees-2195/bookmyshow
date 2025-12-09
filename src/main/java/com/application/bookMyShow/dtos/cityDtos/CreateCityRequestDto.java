package com.application.bookMyShow.dtos.cityDtos;

import com.application.bookMyShow.models.City;
import lombok.Data;

@Data
public class CreateCityRequestDto {
    private CityRequestDto city;

    public static City convertToCity(CityRequestDto request){
        return CityRequestDto.convertToCity(request);
    }
}
