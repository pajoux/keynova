package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class ColorProvider {
    abstract public void setStartTime(Long t);
    abstract public int getColor(Long t);
    abstract public ColorProvider getCopy();
    abstract public AlphaProvider getAlphaProvider();
    abstract public void setColorStartTime(int color, Long t);
    abstract public Long getStartTime();
}
