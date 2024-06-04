package ru.practicum.users;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(NewUserDto newUserDto);

    UserDto toDTO(User user);

    List<UserDto> toListDTO(List<User> users);
}