package com.eden.gallery.viewmodel;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UploadImageVM {

    private String id;
    private String url;
    private String secureUrl;
    private String publicId;
    private String format;
    private Integer width;
    private Integer height;
    private LocalDateTime createdAt;
}
