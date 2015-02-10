package com.inbiqeba.galk.map.coordinates;

import com.inbiqeba.galk.map.Coordinates;

public class Transform implements Coordinates
{
  private Coordinates src;
  private String srcType, destType;

  public Transform(Coordinates src, String srcType, String destType)
  {
    this.src = src;
    this.srcType = srcType;
    this.destType = destType;
  }

  @Override
  public String toJavaScript()
  {
    return "ol.proj.transform(" + src.toJavaScript() + ", '" + srcType + "', '" + destType + "')";
  }
}
