package com.yannisbecker.cryptopiatracker;

import android.content.Context;
import android.support.design.widget.BaseTransientBottomBar;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @TrackerApplicationScope
    @ApplicationContext
    Context context(){
        return context;
    }
}
