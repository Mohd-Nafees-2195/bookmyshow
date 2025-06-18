package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.LanguageRequestDto;
import com.application.bookMyShow.dtos.LanguageResponseDto;
import com.application.bookMyShow.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping("/add")
    public ResponseEntity<LanguageResponseDto> addLanguage(@RequestBody LanguageRequestDto requestDto){
        return languageService.addLanguage(requestDto);
    }
}
