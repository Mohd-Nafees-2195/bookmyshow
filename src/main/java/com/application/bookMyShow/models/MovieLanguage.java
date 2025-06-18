package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.MovieType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MovieLanguage extends BaseModel{

    @Enumerated(EnumType.ORDINAL)
    private MovieType movieType;

    private boolean isAudio;
    private boolean isSubtitle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id",nullable = false)
    @JsonBackReference(value = "movie-language")
    private Movie movie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "language_id",nullable = false)
    @JsonBackReference(value = "language-movie")
    private Language language;
}
