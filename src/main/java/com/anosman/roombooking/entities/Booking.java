package com.anosman.roombooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @RestResource(exported = false)
    private Room room;
    @ManyToOne
    @RestResource(exported = false)
    private User user;

    private String layout;
    private String title;
    //@Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer participants;

    public Booking(Room room, User user, String title,
                   LocalDate date, LocalTime startTime, LocalTime endTime,
                   Integer participants) {
        this.room = room;
        this.user = user;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

}

