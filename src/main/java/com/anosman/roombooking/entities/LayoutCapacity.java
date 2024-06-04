package com.anosman.roombooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LayoutCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String layout;
    private Integer capacity;

    public LayoutCapacity(String layout, Integer capacity) {
        this.layout = layout;
        this.capacity = capacity;
    }
    public enum Layout {

        THEATER("Theater"),
        USHAPE("U-Shape"),
        BOARD("Board Meeting");

        private String description;

        Layout(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

    }
}



