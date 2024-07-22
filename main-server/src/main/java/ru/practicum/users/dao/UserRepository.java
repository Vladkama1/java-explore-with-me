package ru.practicum.users.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.users.model.User;
import ru.practicum.util.PaginationSetup;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByIdIn(List<Long> ids, PaginationSetup paginationSetup);
}
