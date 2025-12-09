package com.application.bookMyShow.dtos.movieDtos;

import com.application.bookMyShow.dtos.genresDtos.GenresDto;
import com.application.bookMyShow.dtos.languageDtos.LanguageDto;
import com.application.bookMyShow.models.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MovieResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long runtime;
    private String imagesUrl;
    private Double voteAverage;
    private Date createdAt;
    private Date updatedAt;
//    private MovieStatus movieStatus;
    private List<LanguageDto> languages;
    private List<GenresDto> genres;


    public static MovieResponseDto convertToMovieResponseDto(Movie movie){
        MovieResponseDto response=new MovieResponseDto();
        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setDescription(movie.getDescription());
        response.setRuntime(movie.getRuntime());
        response.setImagesUrl(movie.getImageUrl());
        response.setCreatedAt(movie.getCreated_at());
        response.setUpdatedAt(movie.getUpdated_at());

        //Get all movie languages
        List<Languages> languages=movie.getLanguages();
        List<LanguageDto> languageDtos=new ArrayList<>();
        languages.forEach(language -> {
            languageDtos.add(LanguageDto.convertToLanguageDto(language));
        });
        response.setLanguages(languageDtos);

        List<Genres> genres=movie.getGenres();
        List<GenresDto> genresDtos=new ArrayList<>();
        genres.forEach(movieGenre->{
            genresDtos.add(GenresDto.convertToGenresDto(movieGenre));
        });
        response.setGenres(genresDtos);
        response.setVoteAverage(5.4);//Here first calculate the average and then add to the response
        return response;
    }
}
