package br.com.ludevsp.domain.dto;

import lombok.Data;

@Data
public class ApiResponse <T>{

    private T data;
    private boolean success = true;

    public ApiResponse(T data, boolean success) {
        this.data = data;
        this.success = success;
    }
    public ApiResponse(T data) {
        this.data = data;
    }
}
