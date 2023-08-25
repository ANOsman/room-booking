package com.anosman.roombooking.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;

    private LayoutCapacity.Layout layout;

    private String title;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer participants;

    public Booking(Room room, User user, LayoutCapacity.Layout layout, String title, Date date, Time startTime, Time endTime, Integer participants) {
        this.room = room;
        this.user = user;
        this.layout = layout;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

}

