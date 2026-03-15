package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidGenresException;
import com.application.bookMyShow.Exceptions.InvalidLanguageException;
import com.application.bookMyShow.Exceptions.InvalidMovieException;
import com.application.bookMyShow.dtos.movieDtos.*;
import com.application.bookMyShow.models.*;
import com.application.bookMyShow.models.enums.MovieStatus;
import com.application.bookMyShow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private GenresRepository genresRepository;


    public ResponseEntity<CreateMovieResponseDto> addMovie(MovieRequestDto request) {

        try{

            //Fetch Genres
            Iterable<Long> genresId=request.getGenresIds();
            List<Genres> genres=genresRepository.findAllById(genresId);
            if(genres.isEmpty()){
                throw new InvalidGenresException("At-least one genre required!!");
            }

            //Fetch Languages
            Iterable<Long> languagesIds=request.getLanguageIds();
            List<Languages> languages=languageRepository.findAllById(languagesIds);
            if(languages.isEmpty()){
                throw new InvalidLanguageException("At-least one language required!!");
            }

            //Create Movie Object
            Movie movie=MovieRequestDto.convertToMovie(request);
            movie.setCreated_at(new Date());
            movie.setUpdated_at(new Date());
            movie.setGenres(genres);
            movie.setLanguages(languages);
            movie=movieRepository.save(movie);

            CreateMovieResponseDto response=new CreateMovieResponseDto();
            response.setMovie(MovieResponseDto.convertToMovieResponseDto(movie));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<GetMovieResponseDto> getMovie(Long id) {
        Optional<Movie> movie=movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new InvalidMovieException("Movie Not Found");
        }
        GetMovieResponseDto response=new GetMovieResponseDto();
        response.setMovie(MovieResponseDto.convertToMovieResponseDto(movie.get()));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<MovieResponseDtos> getAllMovie() {
        List<Movie> movies=movieRepository.findAll();
        MovieResponseDtos response=new MovieResponseDtos();
        response.setMovies(new ArrayList<>());
        movies.forEach(movie -> {
            MovieResponseDto movieDto=MovieResponseDto.convertToMovieResponseDto(movie);
            response.getMovies().add(movieDto);
        });
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<MovieResponseDtos> getAllActiveMovies() {
        List<Movie> movies=movieRepository.findByMovieStatus(MovieStatus.ACTIVE);
        MovieResponseDtos response=new MovieResponseDtos();
        response.setMovies(new ArrayList<>());
        movies.forEach(movie -> {
            MovieResponseDto movieDto=MovieResponseDto.convertToMovieResponseDto(movie);
            response.getMovies().add(movieDto);
        });
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
