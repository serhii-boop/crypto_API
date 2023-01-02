package com.oril.cryptoapp.config;

import com.oril.cryptoapp.api.TokenPriceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Bean
    public TokenPriceApi getTokenPriceApi() {
        return new Retrofit.Builder()
                .baseUrl("https://cex.io/api/ticker/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(TokenPriceApi.class);
    }

}
