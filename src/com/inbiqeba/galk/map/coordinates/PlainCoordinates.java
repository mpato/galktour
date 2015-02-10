package com.inbiqeba.galk.map.coordinates;

import com.inbiqeba.galk.map.Coordinates;

public class PlainCoordinates implements Coordinates
{
  private double x, y;

  public PlainCoordinates(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toJavaScript()
  {
    return String.format("[%.4f, %.4f]", x, y);
  }
}
