package com.oril.cryptoapp.controller;

import com.oril.cryptoapp.entity.response.CurrencyPriceRS;
import com.oril.cryptoapp.service.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/cryptocurrencies/minprice")
    public ResponseEntity<CurrencyPriceRS> getMinCurrencyPrice(@RequestParam("name") String currencyName) {
        return ResponseEntity.ok(currencyInfoService.getMinCurrencyPrice(currencyName));
    }

    @GetMapping("/cryptocurrencies/maxprice")
    public ResponseEntity<CurrencyPriceRS> getMaxCurrencyPrice(@RequestParam("name") String currencyName) {
        return ResponseEntity.ok(currencyInfoService.getMaxCurrencyPrice(currencyName));
    }

    @GetMapping("/cryptocurrencies")
    public ResponseEntity<List<CurrencyPriceRS>> getCurrencyList(@RequestParam("name") String currencyName,
                                                                 @RequestParam(value = "page", required = false, defaultValue = "${pagination.number}") int pageNumber,
                                                                 @RequestParam(value = "size", required = false, defaultValue = "${pagination.size}") int pageSize) {
        return ResponseEntity.ok(currencyInfoService.getAllByCurrency(currencyName, pageNumber, pageSize));
    }

}
