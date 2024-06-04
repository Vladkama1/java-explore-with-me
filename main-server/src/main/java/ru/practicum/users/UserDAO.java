package ru.practicum.users;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.util.PaginationSetup;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    Page<User> findAllByIdIn(List<Long> ids, PaginationSetup paginationSetup);
}
