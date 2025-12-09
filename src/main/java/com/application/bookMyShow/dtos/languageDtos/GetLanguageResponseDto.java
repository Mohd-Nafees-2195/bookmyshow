package com.application.bookMyShow.dtos.languageDtos;

import com.application.bookMyShow.models.Languages;
import lombok.Data;

@Data
public class GetLanguageResponseDto {
    private LanguageResponseDto language;

    private LanguageResponseDto convertToLanguageResponseDto(Languages languages){
        return LanguageResponseDto.convertToLanguageResponseDto(languages);
    }
}
