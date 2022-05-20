package com.bmsit.bmsitapi.model;

import java.util.List;

public class ErrorResponse {

    private String message;
    private List<String> errorDetails;

    public ErrorResponse(String message, List<String> errorDetails) {
        this.message = message;
        this.errorDetails = errorDetails;
    }
}
