package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class RainbowColorProvider extends ColorProvider {
    private final long cycleLengthMs;
    private final double f;
    private final double phase;
    private final AlphaProvider alphaProvider;
    private Long startTime;

    public RainbowColorProvider(Long cycleLengthMs, double phase, AlphaProvider alphaProvider) {
        this.cycleLengthMs = cycleLengthMs;
        this.f = Math.PI * 2 / cycleLengthMs;
        this.phase = phase;
        this.alphaProvider = alphaProvider;
        this.startTime = new Long(-1);
    }

    @Override
    public void setStartTime(Long t) {
        startTime = t;
        alphaProvider.setStartTime(t);
    }

    @Override
    public int getColor(Long t) {
        return Color.getRainbowColor(t, cycleLengthMs, f, phase, alphaProvider.getAlpha(t));
    }

    @Override
    public ColorProvider getCopy() {
        return new RainbowColorProvider(cycleLengthMs, phase, alphaProvider.getCopy());
    }

    @Override
    public void setColorStartTime(int color, Long t) {
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
