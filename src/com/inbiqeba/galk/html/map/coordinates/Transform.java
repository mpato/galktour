package com.inbiqeba.galk.html.map.coordinates;

import com.inbiqeba.galk.html.JavaScriptSnippet;
import com.inbiqeba.galk.html.map.Coordinates;

public class Transform implements Coordinates
{
  private Coordinates src;
  private String srcType, destType;

  public Transform(Coordinates src, String srcType, String destType)
  {
    this.src = src;
    this.srcType = srcType;
    this.destType = destType;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    JavaScriptSnippet snippet;
    snippet = new JavaScriptSnippet();
    snippet.add("ol.proj.transform(");
    snippet.add(src.toJavaScript());
    snippet.add(", '" + srcType + "', '" + destType + "')");
    return snippet;
  }
}
