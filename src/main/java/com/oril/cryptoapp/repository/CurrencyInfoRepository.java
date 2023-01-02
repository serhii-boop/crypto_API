package com.oril.cryptoapp.repository;

import com.oril.cryptoapp.entity.api.CurrencyInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyInfoRepository extends MongoRepository<CurrencyInfo, String> {
}
