package br.com.ludevsp.api.controller;


import br.com.ludevsp.api.controller.mapper.UserMapper;
import br.com.ludevsp.api.dto.UserQueryDTO;
import br.com.ludevsp.api.dto.UserRequestDto;
import br.com.ludevsp.api.dto.UserResponseDto;
import br.com.ludevsp.application.usecase.UserUseCaseImpl;
import br.com.ludevsp.domain.dto.ApiResponse;
import br.com.ludevsp.domain.entities.Movie;
import br.com.ludevsp.domain.entities.UserMovie;
import br.com.ludevsp.domain.interfaces.usecase.UserUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserUseCase userService;

    public UserController(UserUseCaseImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create_user", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@RequestBody UserRequestDto userRequestDto) {
        var user = userService.createUser(userRequestDto.toEntity());
        return new ResponseEntity<>(new ApiResponse<>(UserMapper.toDto(user)), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse<String>> deleteUser(@RequestParam Number id_user) {
        userService.deleteUser(id_user.longValue());
        return new ResponseEntity<>(new ApiResponse<>("user successfully deleted"), HttpStatus.OK);

    }

    @RequestMapping(value = "/update_user", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@RequestParam Number id, @RequestBody UserRequestDto userRequestDto) {
        var userEntity = userRequestDto.toEntity();
        userEntity.setUserId(id.longValue());
        var user = userService.updateUser(userEntity);
        return new ResponseEntity<>(new ApiResponse<>(UserMapper.toDto(user)), HttpStatus.OK);

    }

    @RequestMapping(value = "/get_user", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUser(@ModelAttribute UserQueryDTO userDTOQuery) {
        var users = userService.getUsers(userDTOQuery);
        return new ResponseEntity<>(new ApiResponse<>(users.stream().map(UserMapper::toDto).toList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/add_favorite_movie", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> addFavoriteMovie(@RequestParam long idUser, @RequestParam String idMovie) throws JsonProcessingException {
        userService.addFavoriteMovie(idUser, idMovie);
        return new ResponseEntity<>(new ApiResponse<>("movie successfully added to favorites"), HttpStatus.OK);

    }
    @RequestMapping(value = "/add_hated_movie", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> addHatedMovie(@RequestParam long idUser, @RequestParam String idMovie) throws JsonProcessingException {
        userService.addHatedMovie(idUser, idMovie);
        return new ResponseEntity<>(new ApiResponse<>("movie successfully added to hated"), HttpStatus.OK);

    }
    @RequestMapping(value = "/add_suggested_movie", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<String>> addSuggestedMovie(@RequestParam long idUser, @RequestParam String idMovie) throws JsonProcessingException {
        userService.addSuggestedMovie(idUser, idMovie);
        return new ResponseEntity<>(new ApiResponse<>("movie successfully added to suggested"), HttpStatus.OK);

    }
    @RequestMapping(value = "/get_favorite_movies", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<Movie>>> getFavoriteMovies(@RequestParam long idUser) {
        var movies = userService.getFavoriteMovies(idUser);
        return new ResponseEntity<>(new ApiResponse<>(movies), HttpStatus.OK);

    }
    @RequestMapping(value = "/get_hated_movies", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<Movie>>> getHatedMovies(@RequestParam long idUser) {
        var movies = userService.getHatedMovies(idUser);
        return new ResponseEntity<>(new ApiResponse<>(movies), HttpStatus.OK);

    }
    @RequestMapping(value = "/get_suggested_movies", method = RequestMethod.GET)
    public ResponseEntity<ApiResponse<List<Movie>>> getSuggestedMovies(@RequestParam long idUser) {
        var movies = userService.getSuggestedMovies(idUser);
        return new ResponseEntity<>(new ApiResponse<>(movies), HttpStatus.OK);

    }


}
