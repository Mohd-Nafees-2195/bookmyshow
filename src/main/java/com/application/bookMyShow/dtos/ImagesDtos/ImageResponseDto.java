package com.application.bookMyShow.dtos.ImagesDtos;

import com.application.bookMyShow.models.Images;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ImageResponseDto {
   private Images images;
}
