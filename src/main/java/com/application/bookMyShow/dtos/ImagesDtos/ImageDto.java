package com.application.bookMyShow.dtos.ImagesDtos;

import com.application.bookMyShow.models.Images;
import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private String name;
    private byte[] imageData;

    public static ImageDto covvertToImageDto(Images images){
        ImageDto response=new ImageDto();
        response.setId(images.getId());
        response.setName(images.getName());
        response.setImageData(images.getImageData());
        return response;
    }
}
