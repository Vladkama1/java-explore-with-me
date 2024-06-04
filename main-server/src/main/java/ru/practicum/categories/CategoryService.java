package ru.practicum.categories;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    void deleteCategory(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    List<CategoryDto> getAllCategory(int from, int size);

    CategoryDto getCategoryById(Long catId);
}