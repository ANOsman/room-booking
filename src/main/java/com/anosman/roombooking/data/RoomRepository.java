package com.anosman.roombooking.data;

import com.anosman.roombooking.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
