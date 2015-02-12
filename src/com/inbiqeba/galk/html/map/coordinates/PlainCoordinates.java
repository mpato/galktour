package com.inbiqeba.galk.html.map.coordinates;

import com.inbiqeba.galk.html.JavaScriptSnippet;
import com.inbiqeba.galk.html.map.Coordinates;

public class PlainCoordinates implements Coordinates
{
  private double x, y;

  public PlainCoordinates(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    return new JavaScriptSnippet(String.format("[%.4f, %.4f]", x, y));
  }
}
