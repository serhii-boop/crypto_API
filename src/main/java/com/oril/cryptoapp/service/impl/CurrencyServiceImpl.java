package com.oril.cryptoapp.service.impl;

import com.oril.cryptoapp.api.TokenPriceApi;
import com.oril.cryptoapp.entity.api.CurrencyInfo;
import com.oril.cryptoapp.entity.response.CurrencyPriceRS;
import com.oril.cryptoapp.exception.CurrencyNotFoundException;
import com.oril.cryptoapp.repository.CurrencyInfoRepository;
import com.oril.cryptoapp.service.CurrencyInfoService;
import com.oril.cryptoapp.validation.RequestCurrencyValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyInfoService {

    private final CurrencyInfoRepository currencyInfoRepository;
    private final TokenPriceApi tokenPriceApi;
    private final RequestCurrencyValidator currencyValidator;

    private final static String USD_PAIR = "%s:USD";
    private final static String SORTING_FIELD = "last";


    @Override
    public void fetchCurrencyInfoToDB(String currency) throws IOException {

        log.info("Getting currency {} from CEX.IO", currency);
        var currencyInfo = tokenPriceApi
                .getPriceInfo(currency.toUpperCase(Locale.ROOT), "USD")
                .execute();

        if (currencyInfo.body() == null) {
            log.error(currencyInfo.errorBody().toString());
        }
        if (currencyInfo.body().getTimestamp() != null) {
            currencyInfoRepository.insert(currencyInfo.body());
        } else {
            throw new CurrencyNotFoundException(currency);
        }
    }

    @Override
    public CurrencyPriceRS getMaxCurrencyPrice(String currency) {
        currencyValidator.checkIfExist(currency);
        var maxCurrency = currencyInfoRepository
                .findTopByPairOrderByLastDesc(String.format(USD_PAIR, currency))
                .orElseThrow(() -> new CurrencyNotFoundException(currency));

        return CurrencyPriceRS.builder()
                .price(maxCurrency.getLast())
                .pairs(maxCurrency.getPair())
                .build();
    }

    @Override
    public CurrencyPriceRS getMinCurrencyPrice(String currency) {
        currencyValidator.checkIfExist(currency);
        var minCurrency = currencyInfoRepository
                .findTopByPairOrderByLastAsc(String.format(USD_PAIR, currency))
                .orElseThrow(() -> new CurrencyNotFoundException(currency));

        return CurrencyPriceRS.builder()
                .price(minCurrency.getLast())
                .pairs(minCurrency.getPair())
                .build();
    }

    @Override
    public List<CurrencyPriceRS> getAllByCurrency(String currency, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(SORTING_FIELD).descending());
        var currencyList = currencyInfoRepository.findAllByPair(String.format(USD_PAIR, currency), pageable);

        return currencyList.getContent()
                .stream()
                .map(curr -> CurrencyPriceRS.builder()
                        .price(curr.getLast())
                        .pairs(curr.getPair())
                        .build())
                .toList();
    }

}
