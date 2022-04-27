package com.eden.gallery.util;

import com.sun.istack.localization.LocalizableMessageFactory;
import lombok.Getter;

@Getter
public enum Message {
    SUCCESS("Request successfully."),
    ENTITY_CREATED("Request was accepted, created entity."),
    ENTITY_UPDATED("Request was accepted, updated entity."),
    ENTITY_NOT_FOUND("Entity not found."),
    ENTITY_DELETED("Entity was successfully deleted.");

    private final String message;

    Message(String message) {
        this.message = message;
    }
}
