package com.anosman.roombooking.data;

import com.anosman.roombooking.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:4200")
public interface RoomRepository extends JpaRepository<Room, Long> {
}
