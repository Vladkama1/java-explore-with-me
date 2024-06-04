package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseError handleNotFoundException(final NotFoundException e) {
        log.error("Exception NotFoundException: {}, статус ответа: {}", e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseError(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseError handleBadRequestException(final BadRequestException e) {
        log.error("Exception BadRequestException: {}, статус ответа: {}", e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseError(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BadRequestException.class)
    public ResponseError handleValidateException(final ValidateException e) {
        log.error("Exception ValidateException: {}, статус ответа: {}", e.getMessage(), HttpStatus.CONFLICT);
        return new ResponseError(e.getMessage());
    }
}

