package com.application.bookMyShow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.el.stream.Optional;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Screen> screens;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id",nullable = false)
    private City cityId;

    @ManyToOne
    private User user;
}

/*
Theatre --> Screen

 */
