package com.eden.gallery.util;

import lombok.Getter;

import javax.management.loading.MLetContent;

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
