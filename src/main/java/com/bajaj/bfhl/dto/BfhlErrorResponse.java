package com.bajaj.bfhl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BfhlErrorResponse {
    @JsonProperty("is_success")
    private boolean isSuccess = false;
    
    private String error;

    public BfhlErrorResponse() {
    }

    public BfhlErrorResponse(String error) {
        this.error = error;
    }

    @JsonProperty("is_success")
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
