package com.application.bookMyShow.dtos.languageDtos;

import com.application.bookMyShow.models.Languages;
import lombok.Data;

@Data
public class CreateLanguageResponseDto {
    private LanguageResponseDto language;

    public static LanguageResponseDto convertToLanguageResponseDto(Languages languages){
        return LanguageResponseDto.convertToLanguageResponseDto(languages);
    }
}
