package com.eden.gallery.util;

import lombok.Getter;

/**
 * Returned response code to client.
 */
@Getter
public enum ResponseCode {
    SUCCESS("200"),
    ACCEPTED("202"),
    NOT_FOUND("404");
    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }
}
