package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.ShowSheetRequestDto;
import com.application.bookMyShow.dtos.ShowSheetResponseDto;
import com.application.bookMyShow.models.ShowSheet;
import com.application.bookMyShow.services.ShowSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("showsheet")
public class ShowSheetController {

    @Autowired
    private ShowSheetService showSheetService;

    @PostMapping("/add")
    public ResponseEntity<ShowSheetResponseDto> addShowSheet(@RequestBody ShowSheetRequestDto requestDto){
        return showSheetService.addShowSheet(requestDto);
    }
}
