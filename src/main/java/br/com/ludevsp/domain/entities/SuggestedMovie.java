package br.com.ludevsp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "suggestions_movies")
public class SuggestedMovie extends BasePreference {
    public SuggestedMovie(List<User> user, List<Movie> movie)
    {
        super(user, movie);
    }
}