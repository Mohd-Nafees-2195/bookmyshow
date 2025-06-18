package com.application.bookMyShow.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Language extends BaseModel{
    private String languageName;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "language-movie")
    private List<MovieLanguage> movies;
}
