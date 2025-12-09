package com.application.bookMyShow.dtos.bookingDto;


import com.application.bookMyShow.models.enums.PaymentMode;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDto {
    private Long userId;
    private List<Long> showSheetIds;
    private PaymentMode paymentMode;
}
