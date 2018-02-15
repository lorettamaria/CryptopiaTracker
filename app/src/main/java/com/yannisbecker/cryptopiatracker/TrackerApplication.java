package com.yannisbecker.cryptopiatracker;

import android.app.Application;

public class TrackerApplication extends Application {

    private TrackerApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerTrackerApplicationComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public TrackerApplicationComponent getComponent(){
        return component;
    }

}
