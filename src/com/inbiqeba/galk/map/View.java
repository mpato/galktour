package com.inbiqeba.galk.map;

import com.inbiqeba.galk.html.JavaScriptComponent;
import com.inbiqeba.galk.html.JavaScriptSnippet;

public class View implements JavaScriptComponent
{
  private Coordinates center;
  private int zoom;

  public View(Coordinates center, int zoom)
  {
    this.center = center;
    this.zoom = zoom;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    JavaScriptSnippet snippet;
    snippet = new JavaScriptSnippet();
    snippet.add("new ol.View({center:");
    snippet.add(center.toJavaScript());
    snippet.add(", zoom:" + zoom +"})");
    return snippet;
  }
}
