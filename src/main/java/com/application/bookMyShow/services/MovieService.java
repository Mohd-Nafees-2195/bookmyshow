package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidLanguageException;
import com.application.bookMyShow.dtos.movieLanguageDtos.MovieLanguageDto;
import com.application.bookMyShow.dtos.movieDtos.MovieRequestDto;
import com.application.bookMyShow.dtos.movieDtos.MovieResponseDto;
import com.application.bookMyShow.models.Language;
import com.application.bookMyShow.models.Movie;
import com.application.bookMyShow.models.MovieLanguage;
import com.application.bookMyShow.repositories.LanguageRepository;
import com.application.bookMyShow.repositories.MovieLanguageRepository;
import com.application.bookMyShow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private MovieLanguageRepository movieLanguageRepository;

    public ResponseEntity<MovieResponseDto> addMovie(MovieRequestDto requestDto) {

        Movie movie=requestDto.getMovie();
        Long time=System.currentTimeMillis();
        movie.setCreated_at(time);
        movie.setUpdated_at(time);
        movieRepository.save(movie);

        //Fetch Language
        MovieLanguageDto movieLanguageDto=requestDto.getMovieLanguageDto();
        Optional<Language> language=languageRepository.findById(movieLanguageDto.getLanguageId());
        if(language.isEmpty()){
            throw new InvalidLanguageException("Invalid Language");
        }
        //Add movie_language first
        MovieLanguage movieLanguage=new MovieLanguage();
        movieLanguage.setLanguage(language.get());
        movieLanguage.setMovie(movie);
        movieLanguage.setMovieType(movieLanguageDto.getMovieType());
        movieLanguage.setSubtitle(movieLanguageDto.getIsSubtitle());
        movieLanguage.setAudio(movieLanguageDto.getIsAudio());
        movieLanguageRepository.save(movieLanguage);

        MovieResponseDto responseDto=new MovieResponseDto();
        responseDto.setMovie(movie);
        responseDto.setMessage("Movie added successfully!!");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
