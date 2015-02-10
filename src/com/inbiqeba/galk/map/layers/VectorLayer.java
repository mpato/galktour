package com.inbiqeba.galk.map.layers;

import com.inbiqeba.galk.map.Layer;
import com.inbiqeba.galk.map.sources.TileSource;
import com.inbiqeba.galk.map.sources.VectorSource;

public class VectorLayer implements Layer
{
  private VectorSource source;

  public VectorLayer(VectorSource source)
  {
    this.source = source;
  }

  @Override
  public String toJavaScript()
  {
    return "new ol.layer.Vector({" +
           "            source: " + source.toJavaScript() +
           "          })";
  }
}
