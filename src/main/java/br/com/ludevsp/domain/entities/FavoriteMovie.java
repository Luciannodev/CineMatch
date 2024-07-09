package br.com.ludevsp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "favorite_movies")
public class FavoriteMovie extends BasePreference {
    public FavoriteMovie(long user, long movie)
    {
        super(user, movie);
    }
}