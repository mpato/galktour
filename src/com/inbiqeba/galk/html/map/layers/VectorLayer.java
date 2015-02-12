package com.inbiqeba.galk.html.map.layers;

import com.inbiqeba.galk.html.JavaScriptSnippet;
import com.inbiqeba.galk.html.map.Layer;
import com.inbiqeba.galk.html.map.sources.VectorSource;

public class VectorLayer implements Layer
{
  private VectorSource source;

  public VectorLayer(VectorSource source)
  {
    this.source = source;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    JavaScriptSnippet snippet;
    snippet = new JavaScriptSnippet();
    snippet.add("new ol.layer.Vector({source: ");
    snippet.add(source.toJavaScript());
    snippet.add("})");
    return  snippet;
  }
}
