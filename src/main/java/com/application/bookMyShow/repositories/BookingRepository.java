package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUserId(Long id);

    List<Booking> findByShowIdIn(List<Long> showIds);
}
