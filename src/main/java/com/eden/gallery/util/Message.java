package com.eden.gallery.util;

import com.sun.istack.localization.LocalizableMessageFactory;
import lombok.Getter;

@Getter
public enum Message {
    SUCCESS("Request successfully."),
    ENTITY_CREATED("Request was accepted, returned creating message id."),
    ENTITY_UPDATED("Request was accepted, returned updating message id."),
    ENTITY_NOT_FOUND("Entity not found."),
    ENTITY_DELETED("Request was accepted, returned deleting message id.");

    private final String message;

    Message(String message) {
        this.message = message;
    }
}
