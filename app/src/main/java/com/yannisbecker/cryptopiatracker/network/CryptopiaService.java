package com.yannisbecker.cryptopiatracker.network;

import com.yannisbecker.cryptopiatracker.pojo.BaseMarket;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CryptopiaService {
    @GET("GetMarkets/{basemarket}")
    Call<BaseMarket> getBaseMarket(@Path("basemarket") String basemarket);
}
