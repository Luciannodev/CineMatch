package br.com.ludevsp.application.useCase;

import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.User;
import br.com.ludevsp.domain.exceptions.UserNotFoundException;
import br.com.ludevsp.domain.interfaces.repositories.UserRepository;
import br.com.ludevsp.domain.interfaces.usecase.UserUseCase;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.List;

@Service
public class UserUseCaseImpl implements UserUseCase {
    private  UserRepository userRepository;

    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User userRequest) {
        User User = userRepository.findByIdentificationNumber(userRequest.getIdentificationNumber());
        if(User != null)
            throw new InvalidParameterException("User already exists");
        return userRepository.save(userRequest);
    }

    @Override
    public void deleteUser(long idUser) {
        var user = userRepository.findByUserId(idUser);
        if(user == null)
            throw new UserNotFoundException("User not found");
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User userRequest) {
        var existingUser = userRepository.findByUserId(userRequest.getUserId());
        if(existingUser == null)
            throw new UserNotFoundException("User not found");
        SetNewValueEntity(userRequest, existingUser);
        return userRepository.save(existingUser);
    }

    private static void SetNewValueEntity(User userRequest, User existingUser) {
        for(Field field : User.class.getDeclaredFields()){
            field.setAccessible(true);
            try {
                Object newValue = field.get(userRequest);
                if(newValue != null)
                    field.set(existingUser, newValue);
            }
            catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
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
