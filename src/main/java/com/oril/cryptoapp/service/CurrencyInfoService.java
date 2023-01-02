package com.oril.cryptoapp.service;

import com.oril.cryptoapp.entity.api.CurrencyInfo;

import java.io.IOException;

public interface CurrencyInfoService {

    void fetchCurrencyInfoToDB(String currency) throws IOException;

}
