package br.com.ludevsp.api.controller;


import br.com.ludevsp.api.controller.mapper.UserMapper;
import br.com.ludevsp.api.dto.UserRequestDto;
import br.com.ludevsp.api.dto.UserResponseDto;
import br.com.ludevsp.application.useCase.UserUseCaseImpl;
import br.com.ludevsp.domain.interfaces.usecase.UserUseCase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserUseCase userService;

    public UserController(UserUseCaseImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create_user", method = RequestMethod.POST)
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        var user = userService.createUser(userRequestDto.toEntity());
        return UserMapper.toDto(user);

    }
}
