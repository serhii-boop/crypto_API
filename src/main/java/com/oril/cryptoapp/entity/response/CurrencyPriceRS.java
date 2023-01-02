package com.oril.cryptoapp.entity.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyPriceRS {

    private BigDecimal price;
    private String pairs;
}
