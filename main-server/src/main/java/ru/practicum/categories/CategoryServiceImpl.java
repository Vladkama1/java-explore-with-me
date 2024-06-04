package ru.practicum.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exception.NotFoundException;
import ru.practicum.exception.ValidateException;
import ru.practicum.util.PaginationSetup;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryMapper.toDTO(categoryDAO.save(categoryMapper.toModel(categoryDto)));
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        categoryDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Категория с id = %d не найдена", id)));
        try {
            categoryDAO.deleteById(id);
        } catch (Exception e) {
            throw new ValidateException(String.format("Категория c id = %d не пустая", id));
        }
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Категория с id = %d не найдена", id)));
        category.setName(categoryDto.getName());
        return categoryMapper.toDTO(categoryDAO.save(category));
    }

    @Override
    public List<CategoryDto> getAllCategory(int from, int size) {
        return categoryDAO.findAll(new PaginationSetup(from, size, Sort.unsorted())).stream()
                .map(x -> categoryMapper.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Категория с id = %d не найдена", id)));
        return categoryMapper.toDTO(category);
    }
}
