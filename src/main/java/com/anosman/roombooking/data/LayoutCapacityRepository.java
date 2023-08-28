package com.anosman.roombooking.data;

import com.anosman.roombooking.entities.LayoutCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="http://localhost:4200")

public interface LayoutCapacityRepository extends JpaRepository<LayoutCapacity, Long> {
}
