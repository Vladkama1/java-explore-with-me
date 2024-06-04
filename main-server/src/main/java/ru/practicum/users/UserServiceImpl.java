package ru.practicum.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exception.NotFoundException;
import ru.practicum.util.PaginationSetup;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;


    @Transactional
    @Override
    public UserDto createUser(NewUserDto userDto) {
        return userMapper.toDTO(userDAO.save(userMapper.toModel(userDto)));
    }

    @Override
    public List<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        if (ids.isEmpty()) {
            return userDAO.findAll(new PaginationSetup(from, size, Sort.unsorted())).stream()
                    .map(x -> userMapper.toDTO(x))
                    .collect(Collectors.toList());
        }
        return userDAO.findAllByIdIn(ids, new PaginationSetup(from, size, Sort.unsorted())).stream()
                .map(x -> userMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        userDAO.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + userId + " не найден"));
        userDAO.deleteById(userId);
    }
}