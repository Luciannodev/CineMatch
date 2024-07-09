package br.com.ludevsp.api.controller.mapper;

import br.com.ludevsp.api.dto.UserResponseDto;
import br.com.ludevsp.domain.entities.User;

public class UserMapper {
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getUserId(),user.getName(), user.getEmail(), user.getPassword(), user.getIdentificationNumber());
    }
}
