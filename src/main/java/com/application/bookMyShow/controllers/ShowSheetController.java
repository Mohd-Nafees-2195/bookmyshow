package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetRequestDto;
import com.application.bookMyShow.dtos.showSheetDtos.ShowSheetResponseDto;
import com.application.bookMyShow.services.ShowSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("showsheets")
public class ShowSheetController {

    @Autowired
    private ShowSheetService showSheetService;

    @PostMapping()
    public ResponseEntity<ShowSheetResponseDto> addShowSheet(@RequestBody ShowSheetRequestDto requestDto){
        return showSheetService.addShowSheet(requestDto);
    }
}
