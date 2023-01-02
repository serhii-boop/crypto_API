package com.oril.cryptoapp.validation;

import com.oril.cryptoapp.enums.Currency;
import com.oril.cryptoapp.exception.CurrencyNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestCurrencyValidator {

    public void checkIfExist(String currency) {
        var isValidCurrency = Arrays.stream(Currency.values())
                .anyMatch(curr -> currency.equalsIgnoreCase(curr.name()));
        if (!isValidCurrency) {
            log.error("Currency {} is not in enum list", currency);
            throw new CurrencyNotFoundException(currency);
        }
    }

}
