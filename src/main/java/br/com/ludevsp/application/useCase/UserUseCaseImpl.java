package br.com.ludevsp.application.useCase;

import br.com.ludevsp.api.dto.UserQueryDTO;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.User;
import br.com.ludevsp.domain.exceptions.UserNotFoundException;
import br.com.ludevsp.domain.interfaces.repositories.FavoriteMoviesRepository;
import br.com.ludevsp.domain.interfaces.repositories.UserRepository;
import br.com.ludevsp.domain.interfaces.services.MovieService;
import br.com.ludevsp.domain.interfaces.usecase.UserUseCase;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.List;

@Service
public class UserUseCaseImpl implements UserUseCase {
    private  UserRepository userRepository;
    private FavoriteMoviesRepository favoriteMoviesRepository;

    private MovieService movieService;

    public UserUseCaseImpl(UserRepository userRepository, FavoriteMoviesRepository favoriteMoviesRepository, MovieService movieService) {
        this.userRepository = userRepository;
        this.favoriteMoviesRepository = favoriteMoviesRepository;
        this.movieService = movieService;
    }

    @Override
    public User createUser(User userRequest) {
        User User = userRepository.findByEmail(userRequest.getEmail());
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
    public List<User> getUsers(UserQueryDTO queryUser) {
        return userRepository.findAll(this.userSpecification(queryUser));
    }

    public Specification<User> userSpecification(UserQueryDTO queryDTO) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            for (Field field : UserQueryDTO.class.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(queryDTO);
                    if (value != null) {
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(field.getName()), value));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            return predicate;
        };
    }

    @Override
    public void addFavoriteMovie(Number userId, String movieName) {
        var user = userRepository.findByUserId(userId.longValue());
        if(user == null)
            throw new UserNotFoundException("User not found");
        var movie = movieService.getMovie(movieName);
        if(movie == null)
            throw new InvalidParameterException("Movie not found");
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
