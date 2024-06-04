package ru.practicum.categories;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toModel(CategoryDto categoryDto);

    CategoryDto toDTO(Category category);

    List<CategoryDto> toListDTO(List<Category> categoryList);
}
