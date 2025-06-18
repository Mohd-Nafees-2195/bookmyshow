package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Language;
import lombok.Data;

@Data
public class LanguageResponseDto {
    private Language language;
    private String message;
}
