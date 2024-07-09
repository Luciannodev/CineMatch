package br.com.ludevsp.api.controller;


import br.com.ludevsp.api.controller.mapper.UserMapper;
import br.com.ludevsp.api.dto.UserRequestDto;
import br.com.ludevsp.api.dto.UserResponseDto;
import br.com.ludevsp.application.useCase.UserUseCaseImpl;
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
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        var user = userService.createUser(userRequestDto.toEntity());
        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.DELETE)
    public ResponseEntity<UserResponseDto> deleteUser(@RequestParam Number id_user) throws AccountNotFoundException {
            userService.deleteUser(id_user.longValue());
            return new ResponseEntity<>(HttpStatus.OK);

    }
}
