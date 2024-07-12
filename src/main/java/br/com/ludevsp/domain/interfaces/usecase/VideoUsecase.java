package br.com.ludevsp.domain.interfaces.usecase;

import br.com.ludevsp.domain.entities.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface VideoUsecase {
    List<Movie> getVideos(String movieName) throws JsonProcessingException;
    Movie getVideoById(String movieId);
}
