package com.eden.gallery.service.impl;

import com.eden.gallery.service.ImageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    @Override
    public String upload(MultipartFile file) {
        log.info("uploaded image: {}, {}, {}", file.getName(), file.getOriginalFilename(), file.getContentType());
        return null;
    }
}
