package ru.practicum.exception;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientsException extends RuntimeException {
    private final String message;
}
