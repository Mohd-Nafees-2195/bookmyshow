package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.Feature;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movie_show")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

    @ManyToOne(optional = false)
    @JoinColumn(name = "screen_id",nullable = false)
    @JsonBackReference
    private Screen screen;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ShowSheet> showSheets;

//    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
//    private List<ShowTiming> showTimings;
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
