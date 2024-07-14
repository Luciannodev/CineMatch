package br.com.ludevsp.domain.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@Data
public class Movie {
    @Id
    @Column(name = "movie_id")
    private Long idMovie;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "original_title", nullable = false)
    private String originalTitle;

    @Column(name = "original_language", nullable = false)
    private String originalLanguage;


    @Column(name = "overview", nullable = false, columnDefinition = "LONGTEXT")
    private String overview;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "voto_average", nullable = false)
    private Float voteAverage;

    @Column(name = "voto_count", nullable = false)
    private Integer voteCount;

    public Movie(Long idMovie, String title, String originalTitle, String originalLanguage, String overview, LocalDate releaseDate, Float voteAverage, Integer voteCount) {
        this.idMovie = idMovie;
        this.title = title;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

}