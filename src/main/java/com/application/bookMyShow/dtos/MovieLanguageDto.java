package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.enums.MovieType;
import lombok.Data;

@Data
public class MovieLanguageDto {
    private Long languageId;
    private Boolean isAudio;
    private Boolean isSubtitle;
    private MovieType movieType;
}
