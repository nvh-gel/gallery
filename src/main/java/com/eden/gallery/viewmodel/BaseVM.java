package com.eden.gallery.viewmodel;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseVM {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
