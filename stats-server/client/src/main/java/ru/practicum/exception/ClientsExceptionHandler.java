package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ClientsExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ClientsException.class)
    public ResponseEntity<ResponseError> handleNotFoundException(final ClientsException e) {
        log.error("Exception ClientsException: {}, статус ответа: {}", e.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(new ResponseError(e.getMessage()), HttpStatus.CONFLICT);
    }
}
