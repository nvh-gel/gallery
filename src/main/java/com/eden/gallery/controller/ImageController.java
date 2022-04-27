package com.eden.gallery.controller;

import com.eden.gallery.service.ImageService;
import com.eden.gallery.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
public class ImageController {

    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ResponseModel> uploadImage(@RequestParam MultipartFile file) {

        return ResponseEntity.accepted().body(ResponseModel.uploaded(imageService.upload(file)));
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
