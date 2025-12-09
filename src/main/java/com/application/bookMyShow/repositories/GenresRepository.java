package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genres,Long> {
}
