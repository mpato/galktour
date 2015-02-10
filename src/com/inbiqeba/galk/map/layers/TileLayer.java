package com.inbiqeba.galk.map.layers;

import com.inbiqeba.galk.map.Layer;
import com.inbiqeba.galk.map.Source;
import com.inbiqeba.galk.map.sources.TileSource;

public class TileLayer implements Layer
{
  private TileSource source;

  public TileLayer(TileSource source)
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
