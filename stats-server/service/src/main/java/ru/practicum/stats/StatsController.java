package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.EndpointHit;
import ru.practicum.ViewStats;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static ru.practicum.Constant.DATE_PATTERN;


@Slf4j
@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService service;

    @PostMapping("/hit")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Информация сохранена")
    public void saveEndpointHit(@Valid @RequestBody EndpointHit endpointHit) {
        log.info("Сохранить EndpointHit {}", endpointHit);
        service.saveStat(endpointHit);
    }

    @GetMapping("/stats")
    public Collection<ViewStats> getViewStats(@RequestParam("start") @DateTimeFormat(pattern = DATE_PATTERN) LocalDateTime start,
                                              @RequestParam("end") @DateTimeFormat(pattern = DATE_PATTERN) LocalDateTime end,
                                              @RequestParam(defaultValue = "") List<String> uris,
                                              @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("Получение статистики с параметрами: дата начала {}, дата окончания {}, список URL-адресов {}, " +
                "уникальные сущности {},", start, end, uris, unique);
        return service.getStats(start, end, uris, unique);
    }
}
