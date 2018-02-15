package com.yannisbecker.cryptopiatracker;

import android.content.Context;

import com.yannisbecker.cryptopiatracker.network.CryptopiaService;
import com.yannisbecker.cryptopiatracker.network.CryptopiaServiceModule;

import dagger.Component;

@TrackerApplicationScope
@Component(modules = {CryptopiaServiceModule.class,ContextModule.class})
public interface TrackerApplicationComponent {
    CryptopiaService getApiService();
    @ApplicationContext Context getContext();
}
