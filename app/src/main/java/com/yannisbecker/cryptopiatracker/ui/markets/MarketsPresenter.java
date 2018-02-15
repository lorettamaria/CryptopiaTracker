package com.yannisbecker.cryptopiatracker.ui.markets;

import com.yannisbecker.cryptopiatracker.RetryableErrorCallback;
import com.yannisbecker.cryptopiatracker.filter.DecVolumeFilter;
import com.yannisbecker.cryptopiatracker.network.CryptopiaServiceHandler;
import com.yannisbecker.cryptopiatracker.pojo.BaseMarket;
import com.yannisbecker.cryptopiatracker.pojo.Market;
import com.yannisbecker.cryptopiatracker.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarketsPresenter extends BasePresenter{

    private View view;

    private CryptopiaServiceHandler cryptopiaServiceHandler;

    public MarketsPresenter(View view, CryptopiaServiceHandler cryptopiaServiceHandler){
        this.view = view;
        this.cryptopiaServiceHandler = cryptopiaServiceHandler;
    }

    @Override
    public void initialize() {
       loadBaseMarket(false);
    }

    public void onBaseMarketChanged(){
        loadBaseMarket(false);
    }

    public void loadBaseMarket(final Boolean silently){

        if(!silently){
            view.clearMarkets();
        }

        if(!view.isShowingList()){
            view.isLoading(true);
            view.hideError();
        }

        cryptopiaServiceHandler.getBaseMarket(view.getSelectedBaseMarket(), new CryptopiaServiceHandler.GetBaseMarketCallback() {
            @Override
            public void onSuccess(BaseMarket market, String baseMarket) {
                if (baseMarket.equals(view.getSelectedBaseMarket())) {
                    view.isLoading(false);

                    if (market != null) {

                        ArrayList<Market> markets = (ArrayList<Market>) market.getMarkets();
                        Collections.sort(markets, new DecVolumeFilter());

                        view.showMarkets(markets);

                    } else {
                        if (silently) {
                            view.onError("API returned 'null'");
                        } else {
                            view.onRetryableError("API returned 'null'", new RetryableErrorCallback() {
                                @Override
                                public void onRetry() {
                                    loadBaseMarket(false);
                                }
                            });
                        }
                    }
                }
            }

                @Override
                public void onError (String error,String baseMarket) {
                    if (baseMarket.equals(view.getSelectedBaseMarket())) {
                        view.isLoading(false);
                        if (silently) {
                            view.onError(error);
                        } else {
                            view.onRetryableError(error, new RetryableErrorCallback() {
                                @Override
                                public void onRetry() {
                                    loadBaseMarket(false);
                                }
                            });
                        }
                    }
                }

        });

    }

    public void reloadBaseMarket(){
        loadBaseMarket(true);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    public interface View{
        String getSelectedBaseMarket();
        void onError(String error);
        void onRetryableError(String error, RetryableErrorCallback callback);
        void showMarkets(List<Market> markets);
        void clearMarkets();
        void isLoading(Boolean visible);
        void hideError();
        Boolean isShowingList();
    }
}
