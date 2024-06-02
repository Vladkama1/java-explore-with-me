package ru.practicum.stats;


import ru.practicum.StatDTO;
import ru.practicum.StatOutDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {
    List<StatOutDTO> findStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);

    void saveStats(StatDTO statDTO);
}
