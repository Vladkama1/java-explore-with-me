package ru.practicum.categories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryControllerAdmin {

    private final CategoryService categoryService;
    static final String CATEGORY_PATH = "/{catId}";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Создание категории {}", categoryDto);
        return categoryService.createCategory(categoryDto);
    }

    @PatchMapping(CATEGORY_PATH)
    public CategoryDto updateCategory(@PathVariable(value = "catId") Long catId,
                                      @Valid @RequestBody CategoryDto categoryDto) {
        log.info("Обновление категории {} с id {}", categoryDto, catId);
        return categoryService.updateCategory(catId, categoryDto);
    }

    @DeleteMapping(CATEGORY_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(value = "catId") Long catId) {
        log.info("Удаление категории с id {}", catId);
        categoryService.deleteCategory(catId);
    }
}
