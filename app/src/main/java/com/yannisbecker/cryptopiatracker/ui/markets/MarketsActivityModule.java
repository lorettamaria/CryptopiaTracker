package com.yannisbecker.cryptopiatracker.ui.markets;

import android.content.Context;

import com.yannisbecker.cryptopiatracker.ApplicationContext;
import com.yannisbecker.cryptopiatracker.TrackerApplicationScope;
import com.yannisbecker.cryptopiatracker.network.CryptopiaService;
import com.yannisbecker.cryptopiatracker.network.CryptopiaServiceHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class MarketsActivityModule {

    private MarketsActivity activity;

    public MarketsActivityModule(MarketsActivity activity){
        this.activity = activity;
    }

    @Provides
    @MarketsActivityScope
    MarketsPresenter marketsPresenter(CryptopiaServiceHandler cryptopiaServiceHandler){
        return new MarketsPresenter(activity, cryptopiaServiceHandler);
    }

    @Provides
    @MarketsActivityScope
    CryptopiaServiceHandler apiServiceHandler(CryptopiaService cryptopiaService){
        return new CryptopiaServiceHandler(cryptopiaService);
    }

    @Provides
    @MarketsActivityScope
    MarketsAdapter marketsAdapter(@ApplicationContext Context context){
        return new MarketsAdapter(context);
    }
}
