package com.yannisbecker.cryptopiatracker.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.yannisbecker.cryptopiatracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        try {
            bindViews();
        } catch (RuntimeException e) {
            Log.e("Exception", e.getMessage());
        }
        setupActionbar();
        injectDependencies();
        if (null != getPresenter()) {
            getPresenter().initialize();
        } else {
            throw new IllegalStateException("Presenter needs to be injected during injectDependencies()");
        }
    }

    protected abstract BasePresenter getPresenter();

    protected abstract @LayoutRes
    int getLayout();

    protected abstract void injectDependencies();

    protected void setupActionbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    // Lifetime Callbacks

    @Override
    protected void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
    }
}

