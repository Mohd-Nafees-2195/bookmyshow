package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.showSheetDtos.*;
import com.application.bookMyShow.services.ShowSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("showsheets")
public class ShowSheetController {

    @Autowired
    private ShowSheetService showSheetService;

    @PostMapping
    public ResponseEntity<CreateShowSheetResponseDto> addShowSheet(@RequestBody CreateShowSheetRequestDto requestDto){
        return showSheetService.addShowSheet(requestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetShowSheetResponseDto> getShowSheet(@PathVariable Long id){
        return showSheetService.getShowSheet(id);
    }

    @GetMapping
    public ResponseEntity<GetShowSheetResponseDtos> getAllShowSheet(){
        return showSheetService.getAllShowSheet();
    }

}
