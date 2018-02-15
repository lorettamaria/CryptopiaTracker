package com.yannisbecker.cryptopiatracker.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yannisbecker.cryptopiatracker.TrackerApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class CryptopiaServiceModule {

    @Provides
    @TrackerApplicationScope
    CryptopiaService cryptopiaService(Retrofit retrofit) {
        return retrofit.create(CryptopiaService.class);
    }

    @Provides
    @TrackerApplicationScope
    Gson gson() {
        return new GsonBuilder().create();
    }

    @Provides
    @TrackerApplicationScope
    Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://www.cryptopia.co.nz/api/")
                .build();
    }

}
