package com.idlecode.keynova.core;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface AlphaProvider {
  public void setStartTime(Long t);
  public float getAlpha(Long t);
  public AlphaProvider getCopy();
  public Long getStartTime();
}
