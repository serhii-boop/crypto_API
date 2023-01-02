package com.oril.cryptoapp.exception;

public class CurrencyNotFoundException extends RuntimeException{
    private final String message;
    public CurrencyNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
