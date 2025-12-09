package com.application.bookMyShow.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.apache.el.stream.Optional;

import java.util.List;


@Getter
@Setter
@Entity
public class City extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "cityId",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Theatre> theatres;
}
