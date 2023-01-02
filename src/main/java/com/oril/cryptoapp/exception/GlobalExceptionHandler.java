package com.oril.cryptoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(CurrencyNotFoundException currencyNotFoundException) {
        return ErrorResponse
                .builder()
                .message(currencyNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(PaginationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePaginationException(PaginationException paginationException) {
        return ErrorResponse
                .builder()
                .message(paginationException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(now())
                .build();
    }
}