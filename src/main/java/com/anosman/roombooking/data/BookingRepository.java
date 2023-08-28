package com.anosman.roombooking.data;

import com.anosman.roombooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:4200")

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
