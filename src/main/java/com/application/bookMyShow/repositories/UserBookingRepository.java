package com.application.bookMyShow.repositories;

import com.application.bookMyShow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookingRepository extends JpaRepository<Booking,Long> {

}
