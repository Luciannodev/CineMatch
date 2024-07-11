package br.com.ludevsp.api.dto;

import br.com.ludevsp.domain.entities.User;

public class UserRequestDto {
    private final String name;
    private final String email;
    private final String password;


    public UserRequestDto(String name, String email, String password, String indentificationNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User toEntity() {
        return new User(name, email, password);
    }
}
