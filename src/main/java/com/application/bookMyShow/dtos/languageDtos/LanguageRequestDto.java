package com.application.bookMyShow.dtos.languageDtos;

import com.application.bookMyShow.models.Languages;
import lombok.Data;

@Data
public class LanguageRequestDto {
    private String name;
    private String code;

    public static Languages convertToLanguage(LanguageRequestDto request){
        Languages newLanguage=new Languages();
        newLanguage.setName(request.getName());
        newLanguage.setCode(request.getCode());
        return newLanguage;
    }
}
