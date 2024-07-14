package br.com.ludevsp.domain.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserMovieKey implements Serializable {
    private Long userId;
    private Long movieId;
    private Byte preferenceId;


}