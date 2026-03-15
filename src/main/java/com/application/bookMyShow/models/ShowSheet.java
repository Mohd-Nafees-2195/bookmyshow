package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.SeatType;
import com.application.bookMyShow.models.enums.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSheet extends BaseModel {
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    private Long price;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSheetStatus;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}

/*
ShowSeat --> Show
1 --> 1
M <-- 1
 */
