package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.City;
import lombok.Data;

@Data
public class CityResponseDto {
    private City city;
    private String message;
}
