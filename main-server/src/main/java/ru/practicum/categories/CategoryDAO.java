package ru.practicum.categories;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryDAO extends JpaRepository<Category, Long> {

}
