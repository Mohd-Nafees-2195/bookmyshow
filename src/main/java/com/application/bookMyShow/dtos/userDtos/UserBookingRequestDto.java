package com.application.bookMyShow.dtos.userDtos;


import com.application.bookMyShow.models.enums.PaymentMode;
import lombok.Data;

import java.util.List;

@Data
public class UserBookingRequestDto {
    private Long userId;
    private List<Long> showSheetIds;
    private PaymentMode paymentMode;
}
