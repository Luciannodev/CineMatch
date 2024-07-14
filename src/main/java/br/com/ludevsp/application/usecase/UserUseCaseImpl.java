package br.com.ludevsp.application.usecase;

import br.com.ludevsp.api.dto.UserQueryDTO;
import br.com.ludevsp.domain.PreferenceEnum;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.Preference;
import br.com.ludevsp.domain.entities.User;
import br.com.ludevsp.domain.entities.UserMovie;
import br.com.ludevsp.domain.exceptions.UserNotFoundException;
import br.com.ludevsp.domain.interfaces.repositories.UserMovieRepository;
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
    private final UserRepository userRepository;

    private final MovieService movieService;

    private final UserMovieRepository userMovieRepository;


    public UserUseCaseImpl(UserRepository userRepository, MovieService movieService, UserMovieRepository userMovieRepository) {
        this.userRepository = userRepository;
        this.movieService = movieService;
        this.userMovieRepository = userMovieRepository;
    }

    @Override
    public User createUser(User userRequest) {
        User User = userRepository.findByEmail(userRequest.getEmail());
        if (User != null)
            throw new InvalidParameterException("User already exists");
        return userRepository.save(userRequest);
    }

    @Override
    public void deleteUser(long idUser) {
        var user = getUser(idUser);
        if (user == null)
            throw new UserNotFoundException("User not found");
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User userRequest) {
        var existingUser = getUser(userRequest.getUserId());
        if (existingUser == null)
            throw new UserNotFoundException("User not found");
        SetNewValueEntity(userRequest, existingUser);
        return userRepository.save(existingUser);
    }

    private static void SetNewValueEntity(User userRequest, User existingUser) {
        for (Field field : User.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object newValue = field.get(userRequest);
                if (newValue != null)
                    field.set(existingUser, newValue);
            } catch (IllegalAccessException e) {
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
    public void addFavoriteMovie(long idUser, String movieName) {
        var movie = getMovie(movieName);
        var user = getUser(idUser);
        var preference = userMovieRepository.findByUserIdAndMovieId(user.getUserId(), movie.getIdMovie());
        if (preference != null) {
            userMovieRepository.delete(preference);
        }
        userMovieRepository.save(new UserMovie(user.getUserId(), movie.getIdMovie(), PreferenceEnum.SUGGESTED));
    }


    @Override
    public void addHatedMovie(long idUser, String idMovie) {
        var movie = getMovie(idMovie);
        var user = getUser(idUser);
        var preference = userMovieRepository.findByUserIdAndMovieId(user.getUserId(), movie.getIdMovie());
        if (preference != null) {
            userMovieRepository.delete(preference);
        }
        userMovieRepository.save(new UserMovie(user.getUserId(), movie.getIdMovie(), PreferenceEnum.HATED));
    }

    @Override
    public void addSuggestedMovie(long idUser, String idMovie) {
        var movie = getMovie(idMovie);
        var user = getUser(idUser);
        var preference = userMovieRepository.findByUserIdAndMovieId(user.getUserId(), movie.getIdMovie());
        if (preference != null) {
            userMovieRepository.delete(preference);
        }
        userMovieRepository.save(new UserMovie(user.getUserId(), movie.getIdMovie(), PreferenceEnum.SUGGESTED));

    }

    private User getUser(long idUser) {

        var user = userRepository.findByUserId(idUser);
        if (user == null)
            throw new UserNotFoundException("User not found");
        return user;
    }

    private Movie getMovie(String idMovie) {
        var movie = movieService.getMovieById(idMovie);
        if (movie == null)
            throw new InvalidParameterException("Movie not found");
        return movie;
    }

    @Override
    public void removeFavoriteMovie(long idUser, long idMovie) {
        var userMovie = getUserMovie(idUser, idMovie);
        userMovieRepository.delete(userMovie);

    }

    @Override
    public void removeHatedMovie(long idUser, long idMovie) {
        var userMovie = getUserMovie(idUser, idMovie);
        userMovieRepository.delete(userMovie);
    }

    @Override
    public void removeSuggestedMovie(long idUser, long movieName) {
        var userMovie = getUserMovie(idUser, movieName);
        userMovieRepository.delete(userMovie);
    }

    private UserMovie getUserMovie(long idUser, long movieName) {
        var userMovie = userMovieRepository.findByUserIdAndMovieId(idUser, movieName);
        if (userMovie == null)
            throw new InvalidParameterException("Preference not found");
        return userMovie;
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
