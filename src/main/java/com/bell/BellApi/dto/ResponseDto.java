package com.bell.BellApi.dto;

/**
 * Wrapper for all DTO except for error's
 */
public class ResponseDto {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
