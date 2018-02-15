package com.yannisbecker.cryptopiatracker.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Market{

    @SerializedName("TradePairId")
    @Expose
    private Integer tradePairId;
    @SerializedName("Label")
    @Expose
    private String label;
    @SerializedName("AskPrice")
    @Expose
    private Double askPrice;
    @SerializedName("BidPrice")
    @Expose
    private Double bidPrice;
    @SerializedName("Low")
    @Expose
    private Double low;
    @SerializedName("High")
    @Expose
    private Double high;
    @SerializedName("Volume")
    @Expose
    private Double volume;
    @SerializedName("LastPrice")
    @Expose
    private Double lastPrice;
    @SerializedName("BuyVolume")
    @Expose
    private Double buyVolume;
    @SerializedName("SellVolume")
    @Expose
    private Double sellVolume;
    @SerializedName("Change")
    @Expose
    private Double change;
    @SerializedName("Open")
    @Expose
    private Double open;
    @SerializedName("Close")
    @Expose
    private Double close;
    @SerializedName("BaseVolume")
    @Expose
    private Double baseVolume;
    @SerializedName("BuyBaseVolume")
    @Expose
    private Double buyBaseVolume;
    @SerializedName("SellBaseVolume")
    @Expose
    private Double sellBaseVolume;

    public Integer getTradePairId() {
        return tradePairId;
    }

    public void setTradePairId(Integer tradePairId) {
        this.tradePairId = tradePairId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getBuyVolume() {
        return buyVolume;
    }

    public void setBuyVolume(Double buyVolume) {
        this.buyVolume = buyVolume;
    }

    public Double getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(Double sellVolume) {
        this.sellVolume = sellVolume;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(Double baseVolume) {
        this.baseVolume = baseVolume;
    }

    public Double getBuyBaseVolume() {
        return buyBaseVolume;
    }

    public void setBuyBaseVolume(Double buyBaseVolume) {
        this.buyBaseVolume = buyBaseVolume;
    }

    public Double getSellBaseVolume() {
        return sellBaseVolume;
    }

    public void setSellBaseVolume(Double sellBaseVolume) {
        this.sellBaseVolume = sellBaseVolume;
    }
}
