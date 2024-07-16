package br.com.ludevsp.infra.service;

import br.com.ludevsp.domain.dto.ApiResponse;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.UserMovie;
import br.com.ludevsp.domain.enums.PreferenceEnum;
import br.com.ludevsp.domain.interfaces.repositories.MovieRespository;
import br.com.ludevsp.domain.interfaces.services.MovieService;
import br.com.ludevsp.domain.interfaces.services.OpenApiService;
import br.com.ludevsp.infra.mapper.ResponseToEntityMapper;
import br.com.ludevsp.infra.service.dto.Choice;
import br.com.ludevsp.infra.service.dto.OpenapiSuggestions;
import br.com.ludevsp.infra.service.dto.ResposeOpenApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenApiImpl implements OpenApiService {
    @Value("${OPENAPI.api.url}")
    private String OPENAI_API_URL;
    @Value("${OPENAPI.api.key}")
    private String OPENAI_API_KEY;

    private ResponseToEntityMapper mapper;
    ObjectMapper Objectmapper = new ObjectMapper();
    private MovieService movieService;

    private MovieRespository movieRespository;

    public OpenApiImpl(ResponseToEntityMapper mapper, MovieService movieService, MovieRespository movieRespository) {
        this.mapper = mapper;
        this.movieService = movieService;
        this.movieRespository = movieRespository;
    }

    @Override
    public List<Movie> suggestMovies(List<UserMovie> movies) throws JsonProcessingException {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        headers.set("Content-Type", "application/json");

        var favoritesMovies = movies.stream().filter(movie -> movie.getPreferenceId().equals(PreferenceEnum.FAVORITE.getId())).map(UserMovie::getMovie).toList();
        String favoriteMoviesString = favoritesMovies.stream()
                .map(Movie::getTitle)
                .collect(Collectors.joining(", "));

        var hatedMovies = movies.stream().filter(movie -> movie.getPreferenceId().equals(PreferenceEnum.HATED.getId())).map(UserMovie::getMovie).toList();
        String hatedMoviesString = hatedMovies.stream()
                .map(Movie::getTitle)
                .collect(Collectors.joining(", "));

        String body = "{\n     \"model\": \"gpt-3.5-turbo\",\n     \"messages\": [\n       " +
                "{\"role\": \"system\", \"content\": \"You are an assistant who suggests movies. And it only returns a list inside a json with the films as a suggestion, it doesn't explain the reasons for the films, there are always 5 films, you get the full name of the films and don't repeat the films that the user likes or doesn't like, it's just based on it for the most effective suggestion.\"},\n      " +
                " {\"role\": \"user\", \"content\": \"Here are my film preferences:\\n films that I like:" +
                "\\n" + favoriteMoviesString +
                "\\nSeries and films that I don't like:" +
                "\\n" + hatedMoviesString +
                "\\nPlease suggest some films and series that I might like based on these preferences.\"}\n     ]," +
                "\n     \"max_tokens\": 200\n   }";

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = client.exchange(
                OPENAI_API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class);
        ResposeOpenApi<List<Choice>> movieResponse = mapper.responseToEntity(response, new TypeReference<>() {
        });
        String content = movieResponse.getChoices().getFirst().getMessage().getContent();
        OpenapiSuggestions<List<String>> suggestions = Objectmapper.readValue(content, new TypeReference<>() {
        });

        List<Movie> suggestionsMovie = suggestions.getSuggestions().stream().map(movie -> {
            try {
                var firstMovie = movieService.getMovies(movie).getFirst();
                if (movieRespository.findById(String.valueOf((firstMovie.getIdMovie()))).isEmpty()) {
                    movieRespository.save(firstMovie);
                }
                return firstMovie;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).toList();


        return suggestionsMovie;
    }
}
