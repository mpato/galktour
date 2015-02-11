package com.inbiqeba.galk.map.layers;

import com.inbiqeba.galk.html.JavaScriptSnippet;
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
