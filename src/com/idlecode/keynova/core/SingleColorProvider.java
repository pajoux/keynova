package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class SingleColorProvider extends ColorProvider {
    private final int color;

    public SingleColorProvider(int color) {
        this.color = color;
    }

    @Override
    public void setStartTime(Long t) {

    }

    @Override
    public int getColor(Long t) {
        return color;
    }
}
