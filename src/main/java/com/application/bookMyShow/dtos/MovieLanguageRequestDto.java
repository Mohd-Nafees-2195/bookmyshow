package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.MovieLanguage;
import com.application.bookMyShow.models.enums.MovieType;
import lombok.Data;

@Data
public class MovieLanguageRequestDto {
    private Long movieId;
    private Long languageId;
    private Boolean isAudio;
    private Boolean isSubtitle;
    private MovieType movieType;
}
