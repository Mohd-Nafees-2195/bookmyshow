package com.application.bookMyShow.dtos.cityDtos;

import com.application.bookMyShow.models.City;
import lombok.Data;

@Data
public class GetCityResponseDto {
    private CityResponseDto city;

    public static CityResponseDto convertToCityResponseDto(City city){
        return CityResponseDto.convertToCityResponseDto(city);
    }
}
