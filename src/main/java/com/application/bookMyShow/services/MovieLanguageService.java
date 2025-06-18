package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidJSONValues;
import com.application.bookMyShow.Exceptions.InvalidLanguageException;
import com.application.bookMyShow.Exceptions.InvalidMovieException;
import com.application.bookMyShow.dtos.MovieLanguageRequestDto;
import com.application.bookMyShow.dtos.MovieLanguageResponseDto;
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
public class MovieLanguageService {

    @Autowired
    private MovieLanguageRepository movieLanguageRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private LanguageRepository languageRepository;

    public ResponseEntity<MovieLanguageResponseDto> addMovieLanguage(MovieLanguageRequestDto requestDto) {
        validate(requestDto);
        MovieLanguage movieLanguage=new MovieLanguage();
        //Fetch Movie
        Optional<Movie> movie=movieRepository.findById(requestDto.getMovieId());
        if(movie.isEmpty()){
           throw new InvalidMovieException("Movie not exist!! Please add movie first");
        }
        movieLanguage.setMovie(movie.get());
        //Fetch Language
        Optional<Language> language=languageRepository.findById(requestDto.getLanguageId());
        if(language.isEmpty()){
            throw new InvalidLanguageException("Languages not exist!! Please add language first!!");
        }
        movieLanguage.setLanguage(language.get());
        movieLanguage.setMovieType(requestDto.getMovieType());
        movieLanguage.setAudio(requestDto.getIsAudio());
        movieLanguage.setSubtitle(requestDto.getIsSubtitle());

        Long time=System.currentTimeMillis();
        movieLanguage.setCreated_at(time);
        movieLanguage.setUpdated_at(time);

        movieLanguageRepository.save(movieLanguage);
        MovieLanguageResponseDto responseDto=new MovieLanguageResponseDto();
        responseDto.setMovieLanguage(movieLanguage);
        responseDto.setMessage("Movie language added successfully!!");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    public void validate(MovieLanguageRequestDto requestDto){
        if(requestDto.getMovieId()==null||requestDto.getLanguageId()==null||requestDto.getMovieType()==null){
            throw new InvalidJSONValues("Payload can not be empty!!");
        }
    }
}
