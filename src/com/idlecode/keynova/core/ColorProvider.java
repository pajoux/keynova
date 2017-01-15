package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class ColorProvider {
    abstract public void setStartTime(Long t);
    abstract public int getColor(Long t);
}
