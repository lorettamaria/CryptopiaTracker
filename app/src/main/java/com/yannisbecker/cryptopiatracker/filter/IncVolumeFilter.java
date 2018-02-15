package com.yannisbecker.cryptopiatracker.filter;

import com.yannisbecker.cryptopiatracker.pojo.Market;

import java.util.Comparator;

public class IncVolumeFilter implements Comparator<Market>{
    @Override
    public int compare(Market m1, Market m2) {
        return m1.getBaseVolume().compareTo(m2.getBaseVolume());
    }
}
