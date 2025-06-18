package com.application.bookMyShow.dtos;

import com.application.bookMyShow.models.Seat;
import com.application.bookMyShow.models.Show;
import com.application.bookMyShow.models.enums.ShowSeatStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ShowSheetRequestDto {
    private Long showId;
    private Long seatId;
    private Long price;
    private ShowSeatStatus showSheetStatus;
}
