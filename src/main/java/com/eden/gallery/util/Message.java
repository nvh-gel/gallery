package com.eden.gallery.util;

import lombok.Getter;

@Getter
public enum Message {
    ENTITY_CREATED("Request was accepted, created entity");

    private final String message;

    Message(String message) {
        this.message = message;
    }
}
