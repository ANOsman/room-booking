package com.anosman.roombooking.data;

import com.anosman.roombooking.entities.Booking;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
@CrossOrigin
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getBookingsByDate(LocalDate date);
}
