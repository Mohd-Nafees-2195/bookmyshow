package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.JwtSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtSessionRepository extends JpaRepository<JwtSession,Long> {
    Optional<JwtSession> findByToken(String token);
}
