package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Movie;
import com.application.bookMyShow.models.enums.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByMovieStatus(MovieStatus movieStatus);
}
