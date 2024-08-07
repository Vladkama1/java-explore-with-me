package ru.practicum.categories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

@Slf4j
@Validated
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryControllerPublic {
    private final CategoryService categoryService;

    @GetMapping
    public Collection<CategoryDto> getCategory(@RequestParam(value = "from", defaultValue = "0")
                                               @PositiveOrZero Integer from,
                                               @RequestParam(value = "size", defaultValue = "10") @Positive Integer size) {
        log.info("Получение категории с параметрами {} и размерами {}", from, size);
        return categoryService.getAllCategory(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        log.info("Получение категории с id = {}", catId);
        return categoryService.getCategoryById(catId);
    }
}
