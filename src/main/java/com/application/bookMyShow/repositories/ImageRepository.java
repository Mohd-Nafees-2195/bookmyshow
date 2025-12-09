package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images,Long> {

}
