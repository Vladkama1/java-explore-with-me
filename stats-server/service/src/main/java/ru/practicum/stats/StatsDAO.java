package ru.practicum.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.StatOutDTO;
import ru.practicum.stats.model.Stats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsDAO extends JpaRepository<Stats, Long> {
    @Query("SELECT new ru.practicum.StatOutDTO(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Stats s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip) DESC")
    List<StatOutDTO> getAllStatsDistinctIp(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.StatOutDTO(s.app, s.uri, COUNT(s.ip)) " +
            "FROM Stats s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.ip) DESC")
    List<StatOutDTO> getAllStats(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.StatOutDTO(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Stats s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "AND s.uri IN (?3) " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip) DESC")
    List<StatOutDTO> getStatsByUrisDistinctIp(LocalDateTime start, LocalDateTime end, List<String> uri);

    @Query("SELECT new ru.practicum.StatOutDTO(s.app, s.uri, COUNT(s.ip)) " +
            "FROM Stats s " +
            "WHERE s.timestamp BETWEEN ?1 AND ?2 " +
            "AND s.uri IN (?3) " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.ip) DESC")
    List<StatOutDTO> getStatsByUris(LocalDateTime start, LocalDateTime end, List<String> uri);
}
