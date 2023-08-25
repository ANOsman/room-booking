package com.anosman.roombooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LayoutCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Layout layout;
    private Integer capacity;

    public LayoutCapacity() {}

    public LayoutCapacity(Layout layout, Integer capacity) {
        this.layout = layout;
        this.capacity = capacity;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
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



