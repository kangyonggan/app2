package com.kangyonggan.model;

import lombok.Data;

/**
 * @author kangyonggan
 * @since 16/5/9
 */
@Data
public class ValidationResponse {

    private String status;
    private String message;

    public ValidationResponse() {
    }

    public ValidationResponse(String status) {
        this.status = status;
    }

}
