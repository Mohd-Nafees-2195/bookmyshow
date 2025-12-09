package com.application.bookMyShow.controllers;

import com.application.bookMyShow.dtos.languageDtos.*;
import com.application.bookMyShow.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping()
    public ResponseEntity<CreateLanguageResponseDto> addLanguage(@RequestBody CreateLanguageRequestDto request){
        return languageService.addLanguage(request.getLanguage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLanguageResponseDto> getLanguage(@RequestParam Long id){
        return languageService.getLanguage(id);
    }

    @GetMapping
    public ResponseEntity<LanguageResponseDtos> getAllLanguage(){
        return languageService.getAllLanguage();
    }
}
