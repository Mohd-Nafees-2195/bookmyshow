package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.ShowSheet;
import lombok.Data;

import java.util.List;

@Data
public class ShowResponseDto {
    private Show show;
    private List<ShowSheet> showSheets;
    private String message;
}
