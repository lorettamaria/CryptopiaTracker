package com.yannisbecker.cryptopiatracker.ui.markets;

import com.yannisbecker.cryptopiatracker.TrackerApplicationComponent;

import dagger.Component;

@MarketsActivityScope
@Component(modules = {MarketsActivityModule.class}, dependencies = TrackerApplicationComponent.class)
public interface MarketsActivityComponent {
    void inject(MarketsActivity activity);
}
