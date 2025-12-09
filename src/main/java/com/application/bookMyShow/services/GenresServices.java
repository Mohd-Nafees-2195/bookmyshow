package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidGenresException;
import com.application.bookMyShow.dtos.genresDtos.*;
import com.application.bookMyShow.models.Genres;
import com.application.bookMyShow.repositories.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GenresServices {

    @Autowired
    private GenresRepository genresRepository;

    public ResponseEntity<CreateGenresResponseDto> addGenres(GenresRequestDto request) {
        Genres genres=GenresRequestDto.convertToGenresDto(request);
        genres.setCreated_at(new Date());
        genres.setUpdated_at(new Date());
        genres.setIsDeleted(false);
        genres=genresRepository.save(genres);
        CreateGenresResponseDto response=new CreateGenresResponseDto();
        response.setGenre(GenresResponseDto.convertToGenresDto(genres));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GetGenresResponseDto> getGenres(Long id) {
        Optional<Genres> genres=genresRepository.findById(id);
        if(genres.isEmpty()){
            throw new InvalidGenresException("Genres Not Found!!");
        }
        GetGenresResponseDto response=new GetGenresResponseDto();
        response.setGenre(GenresResponseDto.convertToGenresDto(genres.get()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GenresResponseDtos> getAllGenres() {
        List<Genres> genres=genresRepository.findAll();
        if(genres.isEmpty()){
            throw new InvalidGenresException("No Genres Found!!");
        }
        GenresResponseDtos response=new GenresResponseDtos();
        response.setGenres(new ArrayList<>());
        genres.forEach(genre -> {
            response.getGenres().add(GenresResponseDto.convertToGenresDto(genre));
        });
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
