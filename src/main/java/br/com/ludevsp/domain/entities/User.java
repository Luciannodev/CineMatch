package br.com.ludevsp.domain.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String identificationNumber;
    private String email;
    private String password;
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    public User() {
    }

    public User(String name, String identificationNumber, String email, String password) {
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
