package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movie_show")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Long startTime;
    private Long endTime;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

    @ManyToOne
    private Screen screen;
}

/*
Show --> Movie
1  --> 1
M  <-- 1
 */

/*
Show --> Screen
1 --> 1
M <-- 1
 */
