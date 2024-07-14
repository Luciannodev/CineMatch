package br.com.ludevsp.domain.interfaces.usecase;

import br.com.ludevsp.api.dto.UserQueryDTO;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface UserUseCase {
    User createUser(User userRequest);
    void deleteUser(long idUser);

    User updateUser(User userRequest);
    List<User> getUsers(UserQueryDTO queryUser);
    void addFavoriteMovie(long idUser, String movieName) throws JsonProcessingException;
    void addHatedMovie(long idUser, String movieName) throws JsonProcessingException;
    void addSuggestedMovie(long idUser, String movieName) throws JsonProcessingException;
    void removeFavoriteMovie(long idUser, long movieName);
    void removeHatedMovie(long idUser, long idMovie);
    void removeSuggestedMovie(long idUser, long movieName);
    List<Movie> getFavoriteMovies(String email);
    List<Movie> getHatedMovies(String email);
    List<Movie> getSuggestedMovies(String email);
}
