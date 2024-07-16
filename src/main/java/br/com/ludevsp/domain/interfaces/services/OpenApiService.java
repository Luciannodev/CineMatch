package br.com.ludevsp.domain.interfaces.services;

import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.UserMovie;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface OpenApiService {
    List<Movie> suggestMovies(List<UserMovie> userMovies) throws JsonProcessingException;
}
