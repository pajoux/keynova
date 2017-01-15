package com.idlecode.keynova.core;

import java.util.List;

/**
 *
 */
public class ListColorProvider extends ColorProvider {
    private final List<Integer> colors;
    private int index;

    public ListColorProvider(List<Integer> colors) {
        this.colors = colors;
        index = 0;
    }

    @Override
    public void setStartTime(Long t) {

    }

    @Override
    public int getColor(Long t) {
        if (index > colors.size() - 1) {
            index = 0;
        }
        return colors.get(index++);
    }
}
