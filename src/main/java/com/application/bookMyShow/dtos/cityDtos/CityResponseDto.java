package com.application.bookMyShow.dtos.cityDtos;

import com.application.bookMyShow.dtos.theatreDtos.TheatreResponseDto;
import com.application.bookMyShow.models.City;
import com.application.bookMyShow.models.Theatre;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityResponseDto {
    private Long id;
    private String name;
    private List<TheatreResponseDto> theatres;

    public static CityResponseDto convertToCityResponseDto(City city){
        CityResponseDto response=new CityResponseDto();
        response.setId(city.getId());
        response.setName(city.getName());

        List<TheatreResponseDto> theatresDto=new ArrayList<>();
        List<Theatre> allTheatres=city.getTheatres();
        if(allTheatres!=null){
            allTheatres.forEach(theatre -> {
                theatresDto.add(TheatreResponseDto.convertToTheatreResponseDto(theatre));
            });
        }
        response.setTheatres(theatresDto);
        return response;
    }
}
