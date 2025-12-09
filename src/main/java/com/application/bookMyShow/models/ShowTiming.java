package com.application.bookMyShow.models;

import com.application.bookMyShow.models.enums.ShowTimingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class ShowTiming extends BaseModel {
    private Date startTime;
    private Date endTime;

    @Enumerated(EnumType.ORDINAL)
    private ShowTimingStatus showTimingStatus;

//    @ManyToOne
//    @JoinColumn(name = "show_id")
//    private Show show;
}
