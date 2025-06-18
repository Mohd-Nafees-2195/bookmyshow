package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Theatre;
import lombok.Data;

@Data
public class TheatreResponseDto {
    private Theatre theatre;
    private String message;
}
