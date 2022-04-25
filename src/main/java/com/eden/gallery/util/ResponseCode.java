package com.eden.gallery.util;

import lombok.Getter;

@Getter
public enum ResponseCode {
    CREATED("202");

    private final String code;

    ResponseCode(String code) {
        this.code = code;
    }


}
