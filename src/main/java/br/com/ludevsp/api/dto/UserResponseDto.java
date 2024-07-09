package br.com.ludevsp.api.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private long userId;
    private  String name;
    private  String email;
    private  String password;

    private  String indentificationNumber;

    public UserResponseDto(long userId,String name, String email, String password, String indentificationNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.indentificationNumber = indentificationNumber;
    }
}
