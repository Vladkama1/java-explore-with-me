package ru.practicum.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@Valid @RequestBody NewUserDto newUserDto) {
        log.info("Создание пользователя {}", newUserDto);
        return userService.createUser(newUserDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "userId") Long userId) {
        log.info("Удаление пользователя по id = {}", userId);
        userService.deleteUserById(userId);
    }

    @GetMapping()
    public Collection<UserDto> getUsers(@RequestParam(defaultValue = "") List<Long> ids,
                                        @RequestParam(value = "from", defaultValue = "0") @PositiveOrZero Integer from,
                                        @RequestParam(value = "size", defaultValue = "10") @Positive Integer size) {
        log.info("Получение пользователей");
        return userService.getUsers(ids, from, size);
    }
}
