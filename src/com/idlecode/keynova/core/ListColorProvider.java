package com.idlecode.keynova.core;

import java.util.List;

/**
 *
 */
public class ListColorProvider extends ColorProvider {
    private final List<Integer> colors;
    private final AlphaProvider alphaProvider;
    private int index;
    private Long startTime;

    public ListColorProvider(List<Integer> colors, AlphaProvider alphaProvider) {
        this.colors = colors;
        this.alphaProvider = alphaProvider;
        this.index = 0;
        this.startTime = new Long(-1);
    }

    @Override
    public void setStartTime(Long t) {
        index++;
        if (index > colors.size() - 1) {
            index = 0;
        }
        startTime = t;
        alphaProvider.setStartTime(t);
    }

    @Override
    public int getColor(Long t) {
        return Color.setA(colors.get(index), alphaProvider.getAlpha(t));
    }

    @Override
    public ColorProvider getCopy() {
        return new ListColorProvider(colors, alphaProvider.getCopy());
    }

    @Override
    public void setColorStartTime(int color, Long t) {
        index = colors.indexOf(color);
        if (index < 0) {
            index = 0;
        }
        this.startTime = t;
        alphaProvider.setStartTime(t);
    }

    @Override
    public Long getStartTime() {
        return startTime;
    }

    @Override
    public AlphaProvider getAlphaProvider() {
        return alphaProvider;
    }
}
