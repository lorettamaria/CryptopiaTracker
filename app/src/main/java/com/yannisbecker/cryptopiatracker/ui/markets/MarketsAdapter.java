package com.yannisbecker.cryptopiatracker.ui.markets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yannisbecker.cryptopiatracker.R;
import com.yannisbecker.cryptopiatracker.pojo.Market;

import java.text.DecimalFormat;
import java.util.List;

public class MarketsAdapter extends RecyclerView.Adapter<MarketsAdapter.MarketViewholder> {

    private List<Market> markets;

    private String baseMarket;

    private DecimalFormat priceFormat = new DecimalFormat("########0.00000000");
    private DecimalFormat volumeFormat = new DecimalFormat("#############0.##");

    private Context context;

    MarketsAdapter(Context context){
        this.context = context;
    }

    public void setMarkets(List<Market> markets, String baseMarket){
        this.markets = markets;
        this.baseMarket = baseMarket;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return markets==null?0:markets.size();
    }

    @Override
    public void onBindViewHolder(MarketViewholder holder, int position) {
        try {
            holder.currencyLabel.setText(markets.get(position).getLabel().split("/")[0]);


            holder.lastPriceLabel.setText(priceFormat.format(markets.get(position).getLastPrice())+ " "+baseMarket);
            holder.volumeLabel.setText("Vol: "+ volumeFormat.format(markets.get(position).getBaseVolume())+ " "+baseMarket);
            holder.changeLabel.setText(String.valueOf(markets.get(position).getChange())+ " %");

            if(markets.get(position).getChange()>=0){
                holder.changeLabel.setTextColor(context.getResources().getColor(R.color.colorChangeGreen));
            }else{
                holder.changeLabel.setTextColor(context.getResources().getColor(R.color.colorChangeRed));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public MarketViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MarketViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_market, parent, false));
    }

    class MarketViewholder extends RecyclerView.ViewHolder{

        TextView currencyLabel;
        TextView lastPriceLabel;
        TextView volumeLabel;
        TextView changeLabel;

        MarketViewholder(View itemView) {
            super(itemView);
            currencyLabel = (TextView) itemView.findViewById(R.id.label);
            lastPriceLabel = (TextView) itemView.findViewById(R.id.lastPrice);
            volumeLabel = (TextView) itemView.findViewById(R.id.volume);
            changeLabel = (TextView) itemView.findViewById(R.id.change);
        }
    }
}
