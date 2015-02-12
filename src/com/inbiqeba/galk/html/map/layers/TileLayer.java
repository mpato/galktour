package com.inbiqeba.galk.html.map.layers;

import com.inbiqeba.galk.html.JavaScriptSnippet;
import com.inbiqeba.galk.html.map.Layer;
import com.inbiqeba.galk.html.map.sources.TileSource;

public class TileLayer implements Layer
{
  private TileSource source;

  public TileLayer(TileSource source)
  {
    this.source = source;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    JavaScriptSnippet snippet;
    snippet = new JavaScriptSnippet();
    snippet.add("new ol.layer.Tile({source: ");
    snippet.add(source.toJavaScript());
    snippet.add("})");

    return  snippet;
  }
}
