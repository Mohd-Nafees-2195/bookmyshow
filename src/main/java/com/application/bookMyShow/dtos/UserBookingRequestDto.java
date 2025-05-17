package com.application.bookMyShow.dtos;


import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.models.User;
import com.application.bookMyShow.models.enums.PaymentMode;
import lombok.Data;

import java.util.List;

@Data
public class UserBookingRequestDto {
    private Long userId;
    private List<Long> showSheetIds;
    private PaymentMode paymentMode;
}
