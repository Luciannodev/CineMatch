package br.com.ludevsp.domain.entities;





import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long  movieId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "original_title", nullable = false)
    private String originalTitle;

    @Column(name = "original_language", nullable = false)
    private String originalLanguage;

    @Column(name = "overview", nullable = false)
    private String overview;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "vote_average", nullable = false)
    private Float voteAverage;

    @Column(name = "vote_count", nullable = false)
    private Integer voteCount;

    public Movie(Long  movieId, String title, String originalTitle, String originalLanguage, String overview, LocalDate releaseDate, Float voteAverage, Integer voteCount) {
        this.movieId = movieId;
        this.title = title;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

}