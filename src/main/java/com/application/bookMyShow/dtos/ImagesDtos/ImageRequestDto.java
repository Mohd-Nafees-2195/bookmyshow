package com.application.bookMyShow.dtos.ImagesDtos;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ImageRequestDto {
    private String name;
    private byte[] imageData;
}
