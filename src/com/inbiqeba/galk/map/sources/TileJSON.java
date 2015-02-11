package com.inbiqeba.galk.map.sources;

import com.inbiqeba.galk.html.JavaScriptSnippet;

public class TileJSON implements TileSource
{
  private String url;
  private String crossOrigin;

  public TileJSON(String url, String crossOrigin)
  {
    this.url = url;
    this.crossOrigin = crossOrigin;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    return "\nnew ol.layer.Tile({" +
           "\n  source: new ol.source.TileJSON({" +
           "\n     url: '" + url + "',"+
           "\n     crossOrigin: '" + crossOrigin +  "'"+
           "\n  })" +
           "\n})";
  }
}
