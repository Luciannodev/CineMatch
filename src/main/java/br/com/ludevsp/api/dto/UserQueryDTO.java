package br.com.ludevsp.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQueryDTO {

    private  Number userId;

    private  String name;
    private  String email;

    private String identificationNumber;
}
