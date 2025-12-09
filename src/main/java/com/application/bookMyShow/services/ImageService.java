package com.application.bookMyShow.services;

import com.application.bookMyShow.dtos.ImagesDtos.ImageResponseDto;
import com.application.bookMyShow.models.Images;
import com.application.bookMyShow.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.Date;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public ResponseEntity<ImageResponseDto> uploadImage(MultipartFile file) throws Exception {
        Images image = new Images();
        image.setName(file.getOriginalFilename());
        image.setIsDeleted(false);
        image.setCreated_at(new Date());
        image.setUpdated_at(new Date());
        image.setImageData(file.getBytes());

       image= imageRepository.save(image);
        ImageResponseDto response=new ImageResponseDto();
        response.setImages(image);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public byte[] downloadImage(Long id) throws Exception {
        Images image = imageRepository.findById(id)
                .orElseThrow(() -> new Exception("Image not found"));
        return image.getImageData();
    }
}
