package ru.practicum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class StatDTO {
    @NotBlank(message = "App don`t null!")
    private String app;

    @NotBlank(message = "Uri don`t null!")
    private String uri;

    @NotBlank(message = "Ip don`t null!")
    private String ip;

    @NotNull(message = "Timestamp don`t null!")
    private String timestamp;
}
