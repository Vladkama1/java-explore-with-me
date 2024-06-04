package ru.practicum.users;

import java.util.List;

public interface UserService {
    UserDto createUser(NewUserDto userDto);

    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    void deleteUserById(Long userId);
}
