package com.oril.cryptoapp.service;

import com.oril.cryptoapp.api.TokenPriceApi;
import com.oril.cryptoapp.entity.api.CurrencyInfo;
import com.oril.cryptoapp.entity.response.CurrencyPriceRS;
import com.oril.cryptoapp.exception.CurrencyNotFoundException;
import com.oril.cryptoapp.repository.CurrencyInfoRepository;
import com.oril.cryptoapp.service.impl.CurrencyServiceImpl;
import com.oril.cryptoapp.validation.PaginationValidator;
import com.oril.cryptoapp.validation.RequestCurrencyValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @Mock
    private CurrencyInfoRepository currencyInfoRepository;
    @Mock
    private TokenPriceApi tokenPriceApi;
    @Mock
    private RequestCurrencyValidator currencyValidator;
    @Mock
    private PaginationValidator paginationValidator;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    private BigDecimal LAST_PRICE = new BigDecimal(100);
    private String PAIR = "BTC:USD";

    @Test
    void checkMaxCurrencyPriceSuccess() {
        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.setLast(LAST_PRICE);
        currencyInfo.setPair(PAIR);
        when(currencyInfoRepository.findTopByPairOrderByLastDesc(anyString())).thenReturn(Optional.of(currencyInfo));

        CurrencyPriceRS actualRS = currencyService.getMaxCurrencyPrice(anyString());

        assertEquals(LAST_PRICE, actualRS.getPrice());
        assertEquals(PAIR, actualRS.getPairs());
    }

    @Test
    void checkMinCurrencyPriceSuccess() {
        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.setLast(LAST_PRICE);
        currencyInfo.setPair(PAIR);
        when(currencyInfoRepository.findTopByPairOrderByLastAsc(anyString())).thenReturn(Optional.of(currencyInfo));

        CurrencyPriceRS actualRS = currencyService.getMinCurrencyPrice(anyString());

        assertEquals(LAST_PRICE, actualRS.getPrice());
        assertEquals(PAIR, actualRS.getPairs());
    }

    @Test
    void checkRepoReturnNullInGetMaxAndMinCurrencyPrice() {
        when(currencyInfoRepository.findTopByPairOrderByLastDesc(anyString())).thenReturn(Optional.empty());
        when(currencyInfoRepository.findTopByPairOrderByLastAsc(anyString())).thenReturn(Optional.empty());

        assertThrows(CurrencyNotFoundException.class, () -> {
            currencyService.getMaxCurrencyPrice(anyString());
        });

        assertThrows(CurrencyNotFoundException.class, () -> {
            currencyService.getMinCurrencyPrice(anyString());
        });
    }

    @Test
    void checkGetAllByCurrencySuccess() {
        when(currencyInfoRepository.findAllByPair(anyString(), any())).thenReturn(getPageCurrencyInfo());
        int pageNum = 1;
        int pageSize = 2;
        var currencyPriceRSList = currencyService.getAllByCurrency("BTC", pageNum, pageSize);
        assertEquals(PAIR, currencyPriceRSList.get(0).getPairs());
        assertEquals(LAST_PRICE, currencyPriceRSList.get(0).getPrice());
    }

    private Page<CurrencyInfo> getPageCurrencyInfo() {
        CurrencyInfo currencyInfo = new CurrencyInfo();
        currencyInfo.setLast(LAST_PRICE);
        currencyInfo.setPair(PAIR);
        var currencyInfoList = List.of(currencyInfo);
        return new PageImpl<>(currencyInfoList);
    }

}