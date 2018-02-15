package com.yannisbecker.cryptopiatracker.network;


import android.util.Log;

import com.yannisbecker.cryptopiatracker.pojo.BaseMarket;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptopiaServiceHandler {

    private CryptopiaService cryptopiaService;

    public CryptopiaServiceHandler(CryptopiaService cryptopiaService){
        this.cryptopiaService = cryptopiaService;
    }

    public void getBaseMarket(final String baseMarket, final GetBaseMarketCallback callback){
        Call<BaseMarket> call = cryptopiaService.getBaseMarket(baseMarket);
        call.enqueue(new Callback<BaseMarket>() {
            @Override
            public void onResponse(Call<BaseMarket> call, Response<BaseMarket> response) {
                Log.e("response",response.message());
                callback.onSuccess(response.body(),baseMarket);
            }

            @Override
            public void onFailure(Call<BaseMarket> call, Throwable t) {
                callback.onError(t.getMessage(),baseMarket);
            }
        });
    }

    public interface GetBaseMarketCallback{
        void onSuccess(BaseMarket market, String baseMarket);
        void onError(String error, String baseMarket);
    }
}
