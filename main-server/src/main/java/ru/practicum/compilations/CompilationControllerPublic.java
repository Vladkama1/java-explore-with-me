package ru.practicum.compilations;

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
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationControllerPublic {

    private final CompilationService serviceCompilation;

    @GetMapping("/{compId}")
    public CompilationDto getCompilation(@PathVariable Long compId) {
        log.info("Получение компиляций с id {}", compId);
        return serviceCompilation.getCompilationById(compId);
    }

    @GetMapping
    public Collection<CompilationDto> getCompilation(@RequestParam(required = false) Boolean pinned,
                                                     @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                     @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получение компиляций с параметрами: pinned {} from {} size {}", pinned, from, size);
        return serviceCompilation.getAllCompilations(pinned, from, size);
    }
}
