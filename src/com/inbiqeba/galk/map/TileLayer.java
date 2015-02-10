package com.inbiqeba.galk.map;

public class TileLayer implements Layer
{
  private Source source;

  public TileLayer(Source source)
  {
    this.source = source;
  }

  @Override
  public String toJavaScript()
  {
    return "new ol.layer.Tile({" +
           "            source: " + source.toJavaScript() +
           "          })";
  }
}
