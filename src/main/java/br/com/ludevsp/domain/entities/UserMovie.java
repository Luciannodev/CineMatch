package br.com.ludevsp.domain.entities;

import br.com.ludevsp.domain.PreferenceEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_movie")
@IdClass(UserMovieKey.class)
@NoArgsConstructor
@Data
public class UserMovie {
    @Id
    @Column(name = "id_user")
    private Long userId;

    @Id
    @Column(name = "movie_id")
    private Long movieId;
    @Id
    @Column(name = "preference_id", nullable = false)
    private Byte preferenceId;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "preference_id", insertable = false, updatable = false)
    private Preference preference;

    public UserMovie(Long userId, Long movieId, PreferenceEnum preferenceEnum) {
        this.userId = userId;
        this.movieId = movieId;
        this.preferenceId = preferenceEnum.getId();
    }
}
