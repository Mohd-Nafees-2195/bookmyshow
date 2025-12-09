package com.application.bookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Images extends BaseModel {
    private String name;

    @Lob
    private byte[] imageData;
}
