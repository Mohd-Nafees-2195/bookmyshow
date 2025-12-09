package com.application.bookMyShow.services;

import com.application.bookMyShow.Exceptions.InvalidLanguageException;
import com.application.bookMyShow.dtos.languageDtos.*;
import com.application.bookMyShow.models.Languages;
import com.application.bookMyShow.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public ResponseEntity<CreateLanguageResponseDto> addLanguage(LanguageRequestDto request) {
        Languages newLanguage=LanguageRequestDto.convertToLanguage(request);
        newLanguage.setCreated_at(new Date());
        newLanguage.setUpdated_at(new Date());
        newLanguage.setIsDeleted(false);
        newLanguage=languageRepository.save(newLanguage);
        CreateLanguageResponseDto response=new CreateLanguageResponseDto();
        response.setLanguage(LanguageResponseDto.convertToLanguageResponseDto(newLanguage));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GetLanguageResponseDto> getLanguage(Long id) {
        Optional<Languages> language=languageRepository.findById(id);
        if(language.isEmpty()){
            throw new InvalidLanguageException("No such language found!!");
        }
        GetLanguageResponseDto response=new GetLanguageResponseDto();
        response.setLanguage(LanguageResponseDto.convertToLanguageResponseDto(language.get()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<LanguageResponseDtos> getAllLanguage() {
        List<Languages> languages=languageRepository.findAll();
        if(languages.isEmpty()){
            throw new InvalidLanguageException("No such language found!!");
        }
        LanguageResponseDtos response=new LanguageResponseDtos();
        response.setLanguages(new ArrayList<>());
        languages.forEach(language -> {
            LanguageResponseDto responseDto=LanguageResponseDto.convertToLanguageResponseDto(language);
            response.getLanguages().add(responseDto);
        });
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
