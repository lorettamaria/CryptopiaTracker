package com.yannisbecker.cryptopiatracker.filter;

import com.yannisbecker.cryptopiatracker.pojo.Market;

import java.util.Comparator;

public class DecVolumeFilter implements Comparator<Market>{
    @Override
    public int compare(Market m1, Market m2) {
        return m2.getBaseVolume().compareTo(m1.getBaseVolume());
    }
}
