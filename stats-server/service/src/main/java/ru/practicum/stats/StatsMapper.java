package ru.practicum.stats;

import lombok.experimental.UtilityClass;
import ru.practicum.EndpointHit;
import ru.practicum.stats.model.Stats;


@UtilityClass
public class StatsMapper {

    public Stats mapToStat(EndpointHit endpointHit) {
        Stats stats = new Stats();
        stats.setApp(endpointHit.getApp());
        stats.setUri(endpointHit.getUri());
        stats.setIp(endpointHit.getIp());
        stats.setTimestamp(endpointHit.getTimestamp());
        return stats;
    }
}
