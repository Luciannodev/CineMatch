package br.com.ludevsp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "suggestions_movies")
public class SuggestedMovie extends BasePreference {
    public SuggestedMovie(long user, long movie)
    {
        super(user, movie);
    }
}