package ru.practicum.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.events.enums.EventState;
import ru.practicum.events.model.Location;

import java.time.LocalDateTime;

import static ru.practicum.constants.Constants.PATTERN_DATE;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {
    Long id;
    String title;
    String annotation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN_DATE)
    LocalDateTime eventDate;

    Location location;
    boolean paid;
    int participantLimit;
    int confirmedRequests;
    long views;
    EventState state;

    @JsonUnwrapped
    AdditionalInformation eventInformation;
}
