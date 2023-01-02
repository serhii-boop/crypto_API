package com.oril.cryptoapp.entity.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CsvExportRq {

    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
