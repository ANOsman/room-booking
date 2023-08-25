package com.anosman.roombooking.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
//@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message ="name cannot be blank.")
    private String name;

    @NotBlank(message="password cannot be blank.")
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}

