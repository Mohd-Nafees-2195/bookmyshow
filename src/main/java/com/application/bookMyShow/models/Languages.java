package com.application.bookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Languages extends BaseModel{
    private String name;
    private String code;

    @ManyToMany(mappedBy = "languages", fetch = FetchType.LAZY)
    private List<Movie> movies = new ArrayList<>();
}
