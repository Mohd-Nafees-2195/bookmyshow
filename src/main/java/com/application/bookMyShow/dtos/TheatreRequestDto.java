package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.City;
import com.application.bookMyShow.models.Theatre;
import lombok.Data;

@Data
public class TheatreRequestDto {
    private Long cityId;
    private String theatreName;
}
