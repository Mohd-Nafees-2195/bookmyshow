package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.MovieStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel {
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Long runtime;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private MovieStatus movieStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_language",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Languages> languages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    private List<Genres> genres;

    private Long ratings;
    private Long votes;

//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
//    @JsonManagedReference(value = "movie-language")
//    private List<MovieLanguage> languages;
//
////    @OneToOne
////    private Images images;
//
//    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
//    @JsonManagedReference(value = "movie-genres")
//    private List<MovieGenres> genres;

}
