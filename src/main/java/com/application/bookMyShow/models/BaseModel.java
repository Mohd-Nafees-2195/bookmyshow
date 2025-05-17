package com.application.bookMyShow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long created_at;
    private Long updated_at;

    public BaseModel(){}
    public BaseModel(Long created_at, Long updated_at) {
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
