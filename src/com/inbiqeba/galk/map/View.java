package com.inbiqeba.galk.map;

import com.inbiqeba.galk.html.JavaScriptComponent;

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
  public String toJavaScript()
  {
    return "new ol.View({" +
                        "          center:" + center.toJavaScript() + ", " +
                        "          zoom: " + zoom +
                        "        })";
  }
}
