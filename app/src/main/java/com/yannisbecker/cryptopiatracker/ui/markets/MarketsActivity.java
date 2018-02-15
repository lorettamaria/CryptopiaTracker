package com.yannisbecker.cryptopiatracker.ui.markets;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.yannisbecker.cryptopiatracker.RetryableErrorCallback;
import com.yannisbecker.cryptopiatracker.TrackerApplication;
import com.yannisbecker.cryptopiatracker.pojo.Market;
import com.yannisbecker.cryptopiatracker.R;
import com.yannisbecker.cryptopiatracker.ui.base.BaseActivity;
import com.yannisbecker.cryptopiatracker.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MarketsActivity extends BaseActivity implements MarketsPresenter.View {

    @BindView(R.id.markets_list)
    RecyclerView marketsList;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    MarketsPresenter presenter;

    @BindView(R.id.error)
    View errorView;

    @BindView(R.id.error_msg)
    TextView errorMsg;

    @BindView(R.id.btn_retry)
    Button errorBtn;

    @Inject
    MarketsAdapter adapter;

    private String selectedBaseMarket = "BTC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        marketsList.setLayoutManager(new LinearLayoutManager(this));
        marketsList.setAdapter(adapter);

        setupBottomNavigation();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reloadBaseMarket();
            }
        });
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }


    private void setupBottomNavigation(){
        final AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem itemBTC = new AHBottomNavigationItem(R.string.title_exchange_btc,
                R.drawable.ic_btc_black_24dp,R.color.colorPrimary);
        AHBottomNavigationItem itemUSDT = new AHBottomNavigationItem(R.string.title_exchange_usdt,
                R.drawable.ic_usdt_black_24dp,R.color.colorPrimary);
        AHBottomNavigationItem itemNZDT = new AHBottomNavigationItem(R.string.title_exchange_nzdt,
                R.drawable.ic_monetization_on_black_24dp,R.color.colorPrimary);
        AHBottomNavigationItem itemLTC = new AHBottomNavigationItem(R.string.title_exchange_ltc,
                R.drawable.ic_ltc_black_24dp,R.color.colorPrimary);
        AHBottomNavigationItem itemDOGE = new AHBottomNavigationItem(R.string.title_exchange_doge,
                R.drawable.ic_doge_black_24dp,R.color.colorPrimary);

        bottomNavigation.addItem(itemBTC);
        bottomNavigation.addItem(itemUSDT);
        bottomNavigation.addItem(itemNZDT);
        bottomNavigation.addItem(itemLTC);
        bottomNavigation.addItem(itemDOGE);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        selectedBaseMarket = "BTC";
                        presenter.onBaseMarketChanged();
                        return true;

                    case 1:
                        selectedBaseMarket = "USDT";
                        presenter.onBaseMarketChanged();
                        return true;

                    case 2:
                        selectedBaseMarket = "NZDT";
                        presenter.onBaseMarketChanged();
                        return true;

                    case 3:
                        selectedBaseMarket = "LTC";
                        presenter.onBaseMarketChanged();
                        return true;

                    case 4:
                        selectedBaseMarket = "DOGE";
                        presenter.onBaseMarketChanged();
                        return true;
                }
                return true;
            }
        });

        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setAccentColor(ContextCompat.getColor(this,R.color.colorPrimary));

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_market;
    }

    @Override
    protected void injectDependencies() {
        MarketsActivityComponent component = DaggerMarketsActivityComponent.builder()
                .marketsActivityModule(new MarketsActivityModule(this))
                .trackerApplicationComponent(((TrackerApplication)getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    @Override
    public void showMarkets(List<Market> markets) {
        adapter.setMarkets(markets,getSelectedBaseMarket());
    }

    @Override
    public void clearMarkets() {
        adapter.setMarkets(null,getSelectedBaseMarket());
    }

    @Override
    public String getSelectedBaseMarket() {
        return selectedBaseMarket;
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void isLoading(Boolean visible) {
        progressBar.setVisibility(visible?View.VISIBLE:View.GONE);
        if(!visible){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRetryableError(String error, final RetryableErrorCallback callback) {
        errorMsg.setText(error);
        errorView.setVisibility(View.VISIBLE);
        errorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onRetry();
            }
        });
    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public Boolean isShowingList() {
        return adapter.getItemCount()>0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_markets,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
