package com.eden.gallery.service;

import com.eden.gallery.viewmodel.UploadImageVM;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    /**
     * Upload images files to server.
     *
     * @param files uploaded files
     * @return list of upload result
     */
    List<UploadImageVM> upload(MultipartFile... files);
}
