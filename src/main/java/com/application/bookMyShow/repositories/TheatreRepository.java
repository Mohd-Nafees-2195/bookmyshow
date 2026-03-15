package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Long> {
    List<Theatre> findByUserId(Long id);
}
