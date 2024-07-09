package br.com.ludevsp.domain.interfaces.usecase;

import br.com.ludevsp.api.dto.UserQueryDTO;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.User;

import java.util.List;

public interface UserUseCase {
    User createUser(User userRequest);
    void deleteUser(long idUser);

    User updateUser(User userRequest);
    List<User> getUsers(UserQueryDTO queryUser);
    void addFavoriteMovie(String email, String movieId);
    void addHatedMovie(String email, String movieId);
    void addSuggestedMovie(String email, String movieId);
    void removeFavoriteMovie(String email, String movieId);
    void removeHatedMovie(String email, String movieId);
    void removeSuggestedMovie(String email, String movieId);
    List<Movie> getFavoriteMovies(String email);
    List<Movie> getHatedMovies(String email);
    List<Movie> getSuggestedMovies(String email);
}
