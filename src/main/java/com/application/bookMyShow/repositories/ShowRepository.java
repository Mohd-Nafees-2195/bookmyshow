package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Show;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface ShowRepository extends JpaRepository<Show,Long> {
//    @Query(value = "SELECT * FROM movie_show s WHERE s.screen_id = :screenId AND s.start_time <= :startTime AND s.end_time >= :endTime",nativeQuery = true)
//    List<Show> findAllByScreenId(@Param("screenId") Long screenId, @Param("startTime") Long startTime, @Param("endTime") Long endTime);

//    @Query(value = "SELECT * FROM movie_show s WHERE s.screen_id = :screenId AND s.created_at >= :currentDate",nativeQuery = true)
//    List<Show> findAllByScreenId(@Param("screenId") Long screenId, @Param("currentDate") Date currentDate);
      List<Show> findAllByScreenId(Long screenId);

//    @Query(value = "SELECT * FROM movie_show s WHERE s.movie_id = :movieId AND s.created_at >= :currentDate",nativeQuery = true)
//    List<Show> findByMovieId(@Param("movieId") Long movieId,@Param("currentDate") Date currentDate);
      List<Show> findByMovieIdAndIsDeleted(Long movieId, Boolean isDeleted);

      List<Show> findAllByScreenIdAndIsDeleted(Long screenId, boolean b);

      List<Show> findByScreen_Theatre_Id(Long theatreId);

    List<Show> findAllByScreen_Theatre_IdIn(List<Long> theatreIds);

    //    @Query("SELECT ms FROM MovieShow ms JOIN ms.screen sc WHERE sc.theatre.id IN :theatreIds")
    @Query(value = "SELECT ms FROM movie_show ms JOIN screen sc ON ms.screen_id = sc.id WHERE sc.theatre_id IN :theatreIds",nativeQuery = true)
    List<Show> findByTheatreIds(@Param("theatreIds") List<Long> theatreIds);
}
