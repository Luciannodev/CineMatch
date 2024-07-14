package br.com.ludevsp.api.controller;

import br.com.ludevsp.domain.dto.ApiResponse;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.interfaces.usecase.VideoUsecase;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoController {
    private VideoUsecase videoUsecase;

    public VideoController(VideoUsecase videoUsecase) {
        this.videoUsecase = videoUsecase;
    }

    @RequestMapping(value = "/get_movies", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<Movie>>> addFavoriteMovie(@RequestParam String movieName) throws JsonProcessingException {
        var movies = videoUsecase.getVideos(movieName);
        return new ResponseEntity<>(new ApiResponse<>(movies), HttpStatus.OK);

    }

    @RequestMapping(value = "/get_movie", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<Movie>> getMovieById(@RequestParam String idMovie) {
        var movie = videoUsecase.getVideoById(idMovie);
        return new ResponseEntity<>(new ApiResponse<>(movie), HttpStatus.OK);
    }
}
