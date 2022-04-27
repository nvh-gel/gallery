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
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.ENTITY_CREATED.getMessage(), data);
    }

    public static ResponseModel ok(Object data) {
        return new ResponseModel(ResponseCode.SUCCESS.getCode(), Message.SUCCESS.getMessage(), data);
    }

    public static ResponseModel updated(Object data) {
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.ENTITY_UPDATED.getMessage(), data);
    }

    public static ResponseModel notFound() {
        return new ResponseModel(ResponseCode.NOT_FOUND.getCode(), Message.ENTITY_NOT_FOUND.getMessage(), null);
    }

    public static ResponseModel deleted(Object data) {
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.ENTITY_DELETED.getMessage(), data);
    }

    public static ResponseModel uploaded(Object data) {
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.UPLOADED.getMessage(), data);
    }
}
