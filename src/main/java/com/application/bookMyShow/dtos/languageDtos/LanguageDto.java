package com.application.bookMyShow.dtos.languageDtos;

import com.application.bookMyShow.models.Languages;
import lombok.Data;

@Data
public class LanguageDto {
    private Long id;
    private String name;
    private String code;

    public static LanguageDto convertToLanguageDto(Languages language){
        LanguageDto response=new LanguageDto();
        response.setId(language.getId());
        response.setName(language.getName());
        response.setCode(language.getCode());
        return response;
    }
}
