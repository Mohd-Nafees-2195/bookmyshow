package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ShowRepository extends JpaRepository<Show,Long> {
    @Query(value = "SELECT * FROM movie_show s WHERE s.screen_id = :screenId AND s.start_time <= :startTime AND s.end_time >= :endTime",nativeQuery = true)
    List<Show> findAllByScreenId(@Param("screenId") Long screenId, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

}
