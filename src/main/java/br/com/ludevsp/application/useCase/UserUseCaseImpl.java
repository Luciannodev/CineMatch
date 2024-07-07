package br.com.ludevsp.application.useCase;

import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.User;
import br.com.ludevsp.domain.interfaces.usecase.UserUseCase;

import java.util.List;

public class UserUseCaseImpl implements UserUseCase {
    @Override
    public User createUser(User userRequest) {

        return new User();
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public void updateUser(User userRequest) {

    }

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void addFavoriteMovie(String email, String movieId) {

    }

    @Override
    public void addHatedMovie(String email, String movieId) {

    }

    @Override
    public void addSuggestedMovie(String email, String movieId) {

    }

    @Override
    public void removeFavoriteMovie(String email, String movieId) {

    }

    @Override
    public void removeHatedMovie(String email, String movieId) {

    }

    @Override
    public void removeSuggestedMovie(String email, String movieId) {

    }

    @Override
    public List<Movie> getFavoriteMovies(String email) {
        return null;
    }

    @Override
    public List<Movie> getHatedMovies(String email) {
        return null;
    }

    @Override
    public List<Movie> getSuggestedMovies(String email) {
        return null;
    }
}
