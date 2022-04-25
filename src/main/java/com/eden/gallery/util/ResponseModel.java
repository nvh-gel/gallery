package com.eden.gallery.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseModel {

    private String code;
    private String message;
    private Object data;

    public static ResponseModel created(Object data) {

        return new ResponseModel(ResponseCode.CREATED.getCode(), Message.ENTITY_CREATED.getMessage(), data);
    }
}
