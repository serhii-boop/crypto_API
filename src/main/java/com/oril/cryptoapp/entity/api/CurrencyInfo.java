package com.oril.cryptoapp.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@Document(collection = "currencyInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyInfo {
    @Id
    private String id;

    private String timestamp;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal last;
    private String pair;

}
