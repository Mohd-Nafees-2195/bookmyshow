package com.application.bookMyShow.dtos.screenDtos;

import com.application.bookMyShow.models.Screen;
import lombok.Data;

@Data
public class ScreenResponseDto {
    private Screen screen;
    private String message;
}
