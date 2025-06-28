package com.application.bookMyShow.services;

import com.application.bookMyShow.dtos.languageDtos.LanguageRequestDto;
import com.application.bookMyShow.dtos.languageDtos.LanguageResponseDto;
import com.application.bookMyShow.models.Language;
import com.application.bookMyShow.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public ResponseEntity<LanguageResponseDto> addLanguage(LanguageRequestDto requestDto) {
        Language newLanguage=requestDto.getLanguage();
        Long time=System.currentTimeMillis();
        newLanguage.setCreated_at(time);
        newLanguage.setUpdated_at(time);
        languageRepository.save(newLanguage);
        LanguageResponseDto responseDto=new LanguageResponseDto();
        responseDto.setLanguage(newLanguage);
        responseDto.setMessage("Language Added Successfully!!");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
