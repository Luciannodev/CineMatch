package br.com.ludevsp.infra.service.dto;

import br.com.ludevsp.domain.entities.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDto {
    @JsonProperty("id")
    private long id;

    @JsonProperty("adult")
    private boolean adult;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("genre_ids")
    private List<Integer> genreIds;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("popularity")
    private double popularity;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @JsonProperty("title")
    private String title;

    @JsonProperty("video")
    private boolean video;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;


    public static Movie toEntity(MovieDto movieDto) {
        return new Movie(
                movieDto.id,
                movieDto.title,
                movieDto.originalTitle,
                movieDto.originalLanguage,
                movieDto.overview,
                movieDto.releaseDate,
                movieDto.voteAverage,
                movieDto.voteCount);

    }
}
