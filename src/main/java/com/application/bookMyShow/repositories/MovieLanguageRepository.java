package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.MovieLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieLanguageRepository extends JpaRepository<MovieLanguage,Long> {
}
