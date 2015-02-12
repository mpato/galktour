package com.inbiqeba.galk.html.map.sources;

import com.inbiqeba.galk.html.JavaScriptSnippet;

public class MapQuestSource implements TileSource
{
  public static final int TYPE_SAT = 0;
  public static final int TYPE_OSM = 1;

  private int type;

  public MapQuestSource(int type)
  {
    this.type = type;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    String typeStr;
    switch(type) {
      case TYPE_OSM:
        typeStr = "osm";
        break;
      default:
        typeStr = "sat";
        break;
    }
    return new JavaScriptSnippet("new ol.source.MapQuest({layer: '" + typeStr + "'})");
  }
}
