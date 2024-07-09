package br.com.ludevsp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "hated_movies")
public class HatedMovie extends BasePreference {
    public HatedMovie(List<User> user, List<Movie> movie) {
        super(user, movie);
    }
}