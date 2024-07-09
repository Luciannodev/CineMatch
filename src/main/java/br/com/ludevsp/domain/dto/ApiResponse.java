package br.com.ludevsp.domain.dto;

import lombok.Data;

@Data
public class ApiResponse <T>{
    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }
}
