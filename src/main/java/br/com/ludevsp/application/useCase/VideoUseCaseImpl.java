package br.com.ludevsp.application.useCase;

import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.interfaces.services.MovieService;
import br.com.ludevsp.domain.interfaces.usecase.VideoUsecase;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class VideoUseCaseImpl implements VideoUsecase {

    private final MovieService movieService;

    public VideoUseCaseImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public List<Movie> getVideos(String movieName) throws JsonProcessingException {
        var movies = movieService.getMovies(movieName);
        if (movies == null)
            throw new InvalidParameterException("Movie not found");
        return movies;
    }

    @Override
    public Movie getVideoById(String movieId) {
        return movieService.getMovieById(movieId);
    }
}
