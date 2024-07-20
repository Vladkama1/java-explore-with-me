package ru.practicum.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.events.model.Location;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

import static ru.practicum.constants.Constants.PATTERN_DATE;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewEventDto {
    @NotBlank
    @Size(max = 2000, min = 20)
    String annotation;

    @NotNull(message = "Field: category. Error: must not be blank. Value: null")
    Long category;

    @NotBlank
    @Size(max = 7000, min = 20)
    String description;

    @NotNull
    @Future
    @JsonFormat(pattern = PATTERN_DATE)
    LocalDateTime eventDate;

    Location location;
    Boolean paid = false;
    @PositiveOrZero
    Integer participantLimit = 0;
    Boolean requestModeration = true;

    @NotBlank
    @Size(max = 120, min = 3)
    String title;
}
