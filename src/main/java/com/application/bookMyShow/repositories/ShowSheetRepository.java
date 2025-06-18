package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.ShowSheet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ShowSheetRepository extends JpaRepository<ShowSheet,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT * FROM show_sheet s WHERE s.id = :id ",nativeQuery = true)
    List<ShowSheet> finByIdsAndLockTheWors(@Param("id") Long id);
}
