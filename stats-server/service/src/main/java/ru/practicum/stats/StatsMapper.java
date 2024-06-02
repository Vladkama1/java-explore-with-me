package ru.practicum.stats;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.practicum.StatDTO;
import ru.practicum.StatOutDTO;
import ru.practicum.stats.model.Stats;


@Mapper(componentModel = "spring")
public interface StatsMapper {
    @Mappings
            ({
                    @Mapping(target = "id", ignore = true),
                    @Mapping(target = "timestamp", source = "timestamp", dateFormat = "yyyy-MM-dd HH:mm:ss")
            })
    Stats toModel(StatDTO statDTO);

    StatOutDTO toOutDTO(Stats stats);
}
