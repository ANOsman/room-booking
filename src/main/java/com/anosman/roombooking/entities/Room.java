package com.anosman.roombooking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "location cannot be blank")
    private String location;
    @OneToMany(cascade = {CascadeType.ALL})
    @RestResource(exported = false)
    private List<LayoutCapacity> layoutCapacity;

    public Room(String name, String location) {
        this.name = name;
        this.location = location;
        layoutCapacity = new ArrayList<>();
        for (LayoutCapacity.Layout layout : LayoutCapacity.Layout.values()) {
            layoutCapacity.add(new LayoutCapacity(layout.getDescription(), 0));
        }
    }
    public void setCapacity(LayoutCapacity capacity) {
        for (LayoutCapacity lc : layoutCapacity) {
            if (lc.getLayout().equalsIgnoreCase(capacity.getLayout())) {
                lc.setCapacity(capacity.getCapacity());
            }
        }
    }
}

