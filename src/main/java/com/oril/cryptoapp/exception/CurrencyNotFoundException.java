package com.oril.cryptoapp.exception;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(String currency) {
        super(String.format("Currency with name %s not found", currency));
    }
}
