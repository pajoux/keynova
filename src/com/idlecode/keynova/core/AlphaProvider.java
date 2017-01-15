package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class AlphaProvider {
  abstract public void setStartTime(Long t);
  abstract public float getAlpha(Long t);
  abstract public AlphaProvider getCopy();
  abstract public Long getStartTime();
}
