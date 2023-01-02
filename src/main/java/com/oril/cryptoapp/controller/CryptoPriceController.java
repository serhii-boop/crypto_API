package com.oril.cryptoapp.controller;

import com.oril.cryptoapp.api.TokenPriceApi;
import com.oril.cryptoapp.entity.api.CurrencyInfo;
import com.oril.cryptoapp.repository.CurrencyInfoRepository;
import com.oril.cryptoapp.service.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CryptoPriceController {

    private final CurrencyInfoService currencyInfoService;

    @PostMapping("/fetch/{currency}")
    public ResponseEntity<Void> fetchCurrencyPriceToDB(@PathVariable("currency") String currency) throws IOException {
        currencyInfoService.fetchCurrencyInfoToDB(currency);
        return ResponseEntity.ok().build();
    }

}
