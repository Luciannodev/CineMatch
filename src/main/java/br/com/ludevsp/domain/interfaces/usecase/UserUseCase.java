package br.com.ludevsp.domain.interfaces.usecase;

import br.com.ludevsp.api.dto.UserQueryDTO;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.User;
import br.com.ludevsp.domain.entities.UserMovie;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface UserUseCase {
    User createUser(User userRequest);
    void deleteUser(long idUser);

    User updateUser(User userRequest);
    List<User> getUsers(UserQueryDTO queryUser);
    void addFavoriteMovie(long idUser, String movieName) throws JsonProcessingException;
    void addHatedMovie(long idUser, String movieName) throws JsonProcessingException;
    void suggestedMovieGenerates(long idUser) throws JsonProcessingException;
    void removePreference(long idUser, long movieName);
    List<Movie> getFavoriteMovies(long idUser);
    List<Movie> getHatedMovies(long idUser);
    List<Movie> getSuggestedMovies(long idUser);
}
