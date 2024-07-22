package ru.practicum.requests.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.requests.enums.EventRequestStatus;

import java.time.LocalDateTime;

import static ru.practicum.constants.Constants.PATTERN_DATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PATTERN_DATE)
    LocalDateTime created;
    Long event;
    Long id;
    Long requester;
    EventRequestStatus status;
}
