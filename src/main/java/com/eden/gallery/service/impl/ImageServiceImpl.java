package com.eden.gallery.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.eden.gallery.service.ImageService;
import com.eden.gallery.viewmodel.UploadImageVM;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Image service implementation.
 */
@Service
@Log4j2
public class ImageServiceImpl implements ImageService {

    private static final String IMAGE_PATH = "images/gallery/";

    private Cloudinary cloudinary;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UploadImageVM> upload(MultipartFile... file) {

        Map<String, Object> params = ObjectUtils.asMap(
                "overwrite", false,
                "resource_type", "auto"
        );

        List<Map<String, Object>> results = new ArrayList<>();
        Arrays.stream(file).forEach(f -> {
            String id = UUID.randomUUID().toString();
            String filePath = IMAGE_PATH + id;
            params.put("public_id", filePath);
            Map<String, Object> result = this.uploadSingleFile(f, params);
            if (result != null) {
                result.put("id", id);
            }
            results.add(result);
        });
        return results.stream().map(this::toUploadVM).collect(Collectors.toList());
    }

    /**
     * Upload a single file to cloudinary.
     *
     * @param file   file to upload
     * @param params upload params
     * @return uploading response
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> uploadSingleFile(MultipartFile file, Map<String, Object> params) {

        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
            log.info("Uploaded file {} successfully.", file.getOriginalFilename());
            return uploadResult;
        } catch (IOException ex) {
            log.error("Error encounter when uploading file {}", file.getOriginalFilename(), ex);
        }
        return null;
    }

    /**
     * Convert upload response to upload view model.
     *
     * @param response upload response
     * @return upload view model
     */
    private UploadImageVM toUploadVM(Map<String, Object> response) {
        if (response == null) {
            return null;
        }
        UploadImageVM vm = new UploadImageVM();
        vm.setId(response.getOrDefault("id", null).toString());
        vm.setUrl(response.getOrDefault("url", "").toString());
        vm.setSecureUrl(response.getOrDefault("secure_url", "").toString());
        vm.setPublicId(response.getOrDefault("public_id", "").toString());
        vm.setFormat(response.getOrDefault("format", "").toString());
        vm.setWidth((Integer) response.getOrDefault("width", 0));
        vm.setHeight((Integer) response.getOrDefault("height", 0));
        vm.setCreatedAt(LocalDateTime.now());
        return vm;
    }

    /**
     * Setter.
     *
     * @param cloudinary injected cloudinary client
     */
    @Autowired
    public void setCloudinary(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
}
