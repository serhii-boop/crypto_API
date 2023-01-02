package com.oril.cryptoapp.repository;

import com.oril.cryptoapp.entity.api.CurrencyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CurrencyInfoRepository extends MongoRepository<CurrencyInfo, String> {

    Page<CurrencyInfo> findAllByPair(String pair, Pageable pageable);

    Optional<CurrencyInfo> findTopByPairOrderByLastDesc(String pair);
    Optional<CurrencyInfo> findTopByPairOrderByLastAsc(String pair);

}
