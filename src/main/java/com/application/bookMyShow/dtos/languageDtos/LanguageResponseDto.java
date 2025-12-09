package com.application.bookMyShow.dtos.languageDtos;

import com.application.bookMyShow.models.Languages;
import lombok.Data;

@Data
public class LanguageResponseDto {
    private Long id;
    private String name;
    private String code;

    public static LanguageResponseDto convertToLanguageResponseDto(Languages languages){
        LanguageResponseDto response=new LanguageResponseDto();
        response.setId(languages.getId());
        response.setName(languages.getName());
        response.setCode(languages.getCode());
        return response;
    }
}
