package com.idlecode.keynova.core;

/**
 *
 */
public class SingleColorProvider implements ColorProvider {
    private int color;
    private final AlphaProvider alphaProvider;
    private Long startTime;

    public SingleColorProvider(int color, AlphaProvider alphaProvider) {
        this.color = color;
        this.alphaProvider = alphaProvider;
    }

    @Override
    public void setStartTime(Long t) {
        startTime = t;
        alphaProvider.setStartTime(t);
    }

    @Override
    public int getColor(Long t) {
        return Color.setA(color, alphaProvider.getAlpha(t));
    }

    @Override
    public ColorProvider getCopy() {
        return new SingleColorProvider(color, alphaProvider.getCopy());
    }

    @Override
    public void setColorStartTime(int color, Long t) {
        this.color = color;
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
