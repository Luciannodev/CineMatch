package br.com.ludevsp.infra.service;

import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.interfaces.services.MovieService;
import br.com.ludevsp.infra.mapper.ResponseToEntityMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl implements MovieService {

    private ResponseToEntityMapper mapper;

    public MovieServiceImpl(ResponseToEntityMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public Movie getMovie(String movieName) {
                RestTemplate restTemplate = new RestTemplate();

                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZDE3NzVkN2JiMThlOTdlNGUxMGE3NzQ5NTk5YTcyOCIsIm5iZiI6MTcxOTc0OTU5Ni4wMTExOTYsInN1YiI6IjY2N2Q5MWY4YTVjNDA3ZDMxMzExZjVjOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.o8c6qeuZjRvuLUaEB5ujv9aDJockM7Pkn0rQfl_fcXk");
                headers.set("accept", "application/json");

                HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

                ResponseEntity<String> response = restTemplate.exchange(
                        "https://api.themoviedb.org/3/search/movie?include_adult=false&language=pt-BR&page=1&query=matrix",
                        HttpMethod.GET, entity, String.class);

                Movie movie = mapper.responseToEntity(response, Movie.class);


                return movie;
            }
        }