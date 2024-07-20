package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointHit;
import ru.practicum.ViewStats;
import ru.practicum.stats.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatsServiceImpl implements StatsService {

    private final StatsDAO repository;

    @Override
    @Transactional
    public void saveStat(EndpointHit dto) {
        Stats stat = repository.save(StatsMapper.mapToStat(dto));
        log.info("Сохранить статистику {}", stat);
    }

    @Override
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        if (uris == null || uris.isEmpty()) {
            if (unique) {
                return repository.getAllStatsDistinctIp(start, end);
            } else {
                return repository.getAllStats(start, end);
            }
        } else {
            if (unique) {
                return repository.getStatsByUrisDistinctIp(start, end, uris);
            } else {
                return repository.getStatsByUris(start, end, uris);
            }
        }
    }
}
