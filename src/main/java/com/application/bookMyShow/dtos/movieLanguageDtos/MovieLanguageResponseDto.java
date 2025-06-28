package com.application.bookMyShow.dtos.movieLanguageDtos;

import com.application.bookMyShow.models.MovieLanguage;
import lombok.Data;

@Data
public class MovieLanguageResponseDto {
//    private Long movieId;
//    private Long languageId;
//    private boolean isAudio;
//    private boolean isSubtitle;
//    private MovieType movieType;
    private MovieLanguage movieLanguage;
    private String message;
}
