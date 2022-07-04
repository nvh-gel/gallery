package com.eden.gallery.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Returned response data to client.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseModel {

    private String code;
    private String message;
    private Object data;

    /**
     * Return a created response.
     *
     * @param data created information
     * @return response data
     */
    public static ResponseModel created(Object data) {
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.ENTITY_CREATED.getMessage(), data);
    }

    /**
     * Return a successful response.
     *
     * @param data retrieved information
     * @return response data
     */
    public static ResponseModel ok(Object data) {
        return new ResponseModel(ResponseCode.SUCCESS.getCode(), Message.SUCCESS.getMessage(), data);
    }

    /**
     * Return an updated response.
     *
     * @param data updated information
     * @return response data
     */
    public static ResponseModel updated(Object data) {
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.ENTITY_UPDATED.getMessage(), data);
    }

    /**
     * Return response for data not found.
     *
     * @return response data
     */
    public static ResponseModel notFound() {
        return new ResponseModel(ResponseCode.NOT_FOUND.getCode(), Message.ENTITY_NOT_FOUND.getMessage(), null);
    }

    /**
     * Return a deleted response.
     *
     * @param data deleted information
     * @return response data
     */
    public static ResponseModel deleted(Object data) {
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.ENTITY_DELETED.getMessage(), data);
    }

    /**
     * Return an uploaded response.
     *
     * @param data uploaded information
     * @return response data
     */
    public static ResponseModel uploaded(Object data) {
        return new ResponseModel(ResponseCode.ACCEPTED.getCode(), Message.UPLOADED.getMessage(), data);
    }
}
