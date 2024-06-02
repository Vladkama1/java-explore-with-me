package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.StatDTO;
import ru.practicum.StatOutDTO;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsDAO statsDAO;
    private final StatsMapper statsMapper;


    @Override
    public List<StatOutDTO> findStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (uris == null || uris.isEmpty()) {
            if (unique) {
                return statsDAO.getAllStatsDistinctIp(start, end);
            } else {
                return statsDAO.getAllStats(start, end);
            }
        } else {
            if (unique) {
                return statsDAO.getStatsByUrisDistinctIp(start, end, uris);
            } else {
                return statsDAO.getStatsByUris(start, end, uris);
            }
        }
    }

    @Override
    @Transactional
    public void saveStats(StatDTO statDTO) {
        statsMapper.toOutDTO(statsDAO.save(statsMapper.toModel(statDTO)));
    }
}
