package com.oril.cryptoapp.service.impl;

import com.oril.cryptoapp.api.TokenPriceApi;
import com.oril.cryptoapp.entity.api.CurrencyInfo;
import com.oril.cryptoapp.exception.CurrencyNotFoundException;
import com.oril.cryptoapp.repository.CurrencyInfoRepository;
import com.oril.cryptoapp.service.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyInfoService {

    private final CurrencyInfoRepository currencyInfoRepository;
    private final TokenPriceApi tokenPriceApi;

    private static final String CURRENCY_EXCEPTION_MSG = "Currency %s was not found";


    @Override
    public void fetchCurrencyInfoToDB(String currency) throws IOException {

        var currencyInfo = tokenPriceApi
                .getPriceInfo(currency.toUpperCase(Locale.ROOT), "USD")
                .execute();

        if (currencyInfo.body() == null) {
            log.error(currencyInfo.errorBody().toString());
        }
        if (currencyInfo.body().getTimestamp() != null) {
            currencyInfoRepository.insert(currencyInfo.body());
        } else {
            throw new CurrencyNotFoundException(String.format(CURRENCY_EXCEPTION_MSG, currency));
        }
    }
}
