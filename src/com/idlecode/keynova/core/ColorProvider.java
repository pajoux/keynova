package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface ColorProvider {
    public void setStartTime(Long t);
    public int getColor(Long t);
    public ColorProvider getCopy();
    public AlphaProvider getAlphaProvider();
    public void setColorStartTime(int color, Long t);
    public Long getStartTime();
}
