package com.application.bookMyShow.dtos.showDtos;

import lombok.Data;

import java.util.List;

@Data
public class ShowResponseDtos {
    private List<ShowResponseDto> shows;
}
