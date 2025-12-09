package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Languages,Long> {
}
