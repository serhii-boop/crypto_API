package com.oril.cryptoapp.api;

import com.oril.cryptoapp.entity.api.CurrencyInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TokenPriceApi {

    @GET("{symbol1}/{symbol2}")
    Call<CurrencyInfo> getPriceInfo(@Path("symbol1") String currency1, @Path("symbol2") String currency2);
}
