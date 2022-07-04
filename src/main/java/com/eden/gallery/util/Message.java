package com.eden.gallery.util;

import lombok.Getter;

/**
 * Response message to client,
 */
@Getter
public enum Message {
    SUCCESS("Request successfully."),
    ENTITY_CREATED("Request was accepted, returned creating message id."),
    ENTITY_UPDATED("Request was accepted, returned updating message id."),
    ENTITY_NOT_FOUND("Entity not found."),
    ENTITY_DELETED("Request was accepted, returned deleting message id."),
    UPLOADED("File was uploaded successfully.");
    private final String message;

    Message(String message) {
        this.message = message;
    }
}
