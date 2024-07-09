package br.com.ludevsp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "hated_movies")
public class HatedMovie extends BasePreference {
    public HatedMovie(long user, long movie) {
        super(user, movie);
    }
}