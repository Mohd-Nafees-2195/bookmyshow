package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.Feature;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

    @ManyToOne(optional = false)
    @JoinColumn(name = "theatre_id",nullable = false) //Create FK
    @JsonBackReference
    private Theatre theatre;
}

/*
Screen --> Seat
1 --> M
1 <-- 1
 */
