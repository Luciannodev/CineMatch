package br.com.ludevsp.api.controller;


import br.com.ludevsp.api.controller.mapper.UserMapper;
import br.com.ludevsp.api.dto.UserRequestDto;
import br.com.ludevsp.api.dto.UserResponseDto;
import br.com.ludevsp.application.useCase.UserUseCaseImpl;
import br.com.ludevsp.domain.dto.ApiResponse;
import br.com.ludevsp.domain.exceptions.UserNotFoundException;
import br.com.ludevsp.domain.interfaces.usecase.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

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
}
