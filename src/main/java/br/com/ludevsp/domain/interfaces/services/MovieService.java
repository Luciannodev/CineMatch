package br.com.ludevsp.domain.interfaces.services;

import br.com.ludevsp.domain.entities.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies(String movieName) throws JsonProcessingException;

    Movie getMovieById(String movieId);
}
