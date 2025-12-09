package com.application.bookMyShow.dtos.languageDtos;

import com.application.bookMyShow.models.Languages;
import lombok.Data;

@Data
public class CreateLanguageRequestDto {
    private LanguageRequestDto language;

    public static Languages convertToLanguage(CreateLanguageRequestDto request){
        return LanguageRequestDto.convertToLanguage(request.getLanguage());
    }
}
