package com.inbiqeba.galk.gui.geometry;

import com.inbiqeba.galk.html.JavaScriptComponent;
import com.inbiqeba.galk.html.JavaScriptSnippet;

public class GeometryPoint implements JavaScriptComponent
{
  private double x, y;

  public GeometryPoint(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    return String.format("new ol.geom.Point([%.4f, %.4f])", x, y);
  }
}
