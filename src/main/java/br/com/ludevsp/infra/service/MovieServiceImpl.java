package br.com.ludevsp.infra.service;

import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.interfaces.services.MovieService;
import br.com.ludevsp.infra.dto.MovieDto;
import br.com.ludevsp.infra.dto.ResponseApi;
import br.com.ludevsp.infra.mapper.ResponseToEntityMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private ResponseToEntityMapper mapper;

    @Value("${TMDB.api.key}")
    private String TMDB_API_SECRET;

    @Value("${TMDB.api.url}")
    private String TMDB_API_URL;

    @Value("${TMDB.api.language}")
    private String LANGUAGE;
    private Integer PAGE = 1;

    public MovieServiceImpl(ResponseToEntityMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<Movie> getMovies(String movieName) {

        List<Movie> allMovies = new ArrayList<>();
        int currentPage = 1;
        UriComponentsBuilder builder;
        builder = UriComponentsBuilder.fromHttpUrl(TMDB_API_URL + "/search/movie")
                .queryParam("query", movieName)
                .queryParam("language", LANGUAGE)
                .queryParam("page", PAGE)
                .queryParam("include_adult", false);
        while (true) {
            ResponseEntity<String> response = callTMDBApi(builder);
            ResponseApi<MovieDto> movieResponse = mapper.responseToEntity(response, new TypeReference<>() {
            });
            addMoviesToList(movieResponse, allMovies);
            if (currentPage == movieResponse.getTotalPages()) {
                break;
            }
            currentPage++;

        }
        return allMovies;
    }


    private static void addMoviesToList(ResponseApi<MovieDto> movieResponse, List<Movie> allMovies) {
        List<MovieDto> moviesDto = movieResponse.getData();
        List<Movie> movies = moviesDto.stream().map(MovieDto::toEntity).toList();
        allMovies.addAll(movies);
    }

    @Override
    public Movie getMovieById(String movieId) {
        UriComponentsBuilder builder;
        builder = UriComponentsBuilder.fromHttpUrl(TMDB_API_URL + "/movie/")
                .path(movieId);
        ResponseEntity<String> response = callTMDBApi(builder);
        MovieDto movieResponse = mapper.responseToEntity(response, new TypeReference<>() {
        });
        return MovieDto.toEntity(movieResponse);
    }

    private ResponseEntity<String> callTMDBApi(UriComponentsBuilder builder) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> parameters = getParametersHttp();
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET, parameters, String.class);
    }

    private HttpEntity<String> getParametersHttp() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TMDB_API_SECRET);
        headers.set("accept", "application/json");
        HttpEntity<String> parameters = new HttpEntity<>("parameters", headers);
        return parameters;
    }


}