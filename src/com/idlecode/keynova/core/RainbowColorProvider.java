package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class RainbowColorProvider extends ColorProvider {
    private final long cycleLengthMs;
    private final double f;
    private final float alpha;

    public RainbowColorProvider(Long cycleLengthMs, float alpha) {
        this.cycleLengthMs = cycleLengthMs;
        this.alpha = alpha;
        f = Math.PI * 2 / cycleLengthMs;
    }

    @Override
    public void setStartTime(Long t) {

    }

    @Override
    public int getColor(Long t) {
        long delta = t % cycleLengthMs;
        double r0 = f * delta + 2;
        double r1 = f * delta + 0;
        double r2 = f * delta + 4;
        float r = (float)(Math.sin(r0) * 0.5 + 0.5);
        float g = (float)(Math.sin(r1) * 0.5 + 0.5);
        float b = (float)(Math.sin(r2) * 0.5 + 0.5);
        return Color.rgba(r, g, b, alpha);
    }
}
