package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.languageDtos.LanguageRequestDto;
import com.application.bookMyShow.dtos.languageDtos.LanguageResponseDto;
import com.application.bookMyShow.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping()
    public ResponseEntity<LanguageResponseDto> addLanguage(@RequestBody LanguageRequestDto requestDto){
        return languageService.addLanguage(requestDto);
    }
}
