package com.application.bookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Genres extends BaseModel{
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private List<Movie> movies = new ArrayList<>();

//    @OneToMany(mappedBy = "genres", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference(value = "genres-movie")
//    private List<MovieGenres> movies;
}

/*
* 1 - M
* 1 - M
* */
