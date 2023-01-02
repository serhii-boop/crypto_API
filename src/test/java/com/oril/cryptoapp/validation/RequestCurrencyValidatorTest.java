package com.oril.cryptoapp.validation;

import com.oril.cryptoapp.exception.CurrencyNotFoundException;
import com.oril.cryptoapp.exception.PaginationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
class RequestCurrencyValidatorTest {

    @InjectMocks
    private RequestCurrencyValidator currencyValidator;

    @Test
    void checkIfValidateCurrency() {
        assertThrows(CurrencyNotFoundException.class, () ->
                currencyValidator.checkIfExist("error"));

        assertDoesNotThrow(() -> currencyValidator.checkIfExist("BTC"));
        assertDoesNotThrow(() -> currencyValidator.checkIfExist("ETH"));
        assertDoesNotThrow(() -> currencyValidator.checkIfExist("XRP"));
    }
}