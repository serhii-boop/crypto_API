package com.oril.cryptoapp.service;

import com.oril.cryptoapp.entity.api.CurrencyInfo;
import com.oril.cryptoapp.entity.response.CurrencyPriceRS;

import java.io.IOException;
import java.util.List;

public interface CurrencyInfoService {

    void fetchCurrencyInfoToDB(String currency) throws IOException;

    CurrencyPriceRS getMaxCurrencyPrice(String currency);

    CurrencyPriceRS getMinCurrencyPrice(String currency);

    List<CurrencyPriceRS> getAllByCurrency(String currency, int pageNumber, int pageSize);
}
