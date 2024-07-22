package ru.practicum.compilations;

import java.util.List;

public interface CompilationService {

    CompilationDto createCompilation(NewCompilationDto compilationDto);

    List<CompilationDto> getAllCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto getCompilationById(Long id);

    void deleteCompilationById(Long compId);

    CompilationDto updateCompilationByID(Long compId, UpdateCompilationRequest compilationDto);
}
