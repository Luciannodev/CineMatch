package br.com.ludevsp.domain.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "favorite_movies")
public class FavoriteMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}