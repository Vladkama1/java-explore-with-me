package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.StatDTO;
import ru.practicum.StatOutDTO;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/{stats}")
    public List<StatOutDTO> findStats(@RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                      @RequestParam() @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                      @RequestParam(required = false) List<String> uris,
                                      @RequestParam(defaultValue = "false") boolean unique) {
        List<StatOutDTO> statOutDTOS = statsService.findStats(start, end, uris, unique);
        log.info("Получение списка статистики: {}.", statOutDTOS.size());
        return statOutDTOS;
    }

    @PostMapping("/{hit}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStats(@Valid @RequestBody StatDTO statDTO) {
        log.info("Внесение в базу статистики по Uri: {}.", statDTO.getUri());
        statsService.saveStats(statDTO);
    }
}
