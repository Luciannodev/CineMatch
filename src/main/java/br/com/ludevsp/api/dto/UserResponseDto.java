package br.com.ludevsp.api.dto;

public class UserResponseDto {
    private  String name;
    private  String email;
    private  String password;

    private  String indentificationNumber;

    public UserResponseDto(String name, String email, String password, String indentificationNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.indentificationNumber = indentificationNumber;
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

    public String getIndentificationNumber() {
        return indentificationNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIndentificationNumber(String indentificationNumber) {
        this.indentificationNumber = indentificationNumber;
    }
}
