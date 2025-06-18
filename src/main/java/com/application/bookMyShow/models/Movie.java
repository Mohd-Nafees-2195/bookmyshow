package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.MovieStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel {
    private String title;
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private MovieStatus movieStatus;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "movie-language")
    private List<MovieLanguage> languages;
}
