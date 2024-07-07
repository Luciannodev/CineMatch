package br.com.ludevsp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "hated_movies")
public class HatedMovie extends BasePreference {
    public HatedMovie(User user, Movie movie) {
        super(user, movie);
    }
}