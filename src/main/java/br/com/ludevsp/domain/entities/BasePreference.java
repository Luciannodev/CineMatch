package br.com.ludevsp.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@MappedSuperclass
public abstract class BasePreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> user;
    @OneToMany
    @JoinColumn(name = "movie_id")
    private List<Movie> movie;


    public BasePreference(List<User> user, List<Movie> movie) {
        this.user = user;
        this.movie = movie;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}