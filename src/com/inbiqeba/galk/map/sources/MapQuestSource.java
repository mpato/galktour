package com.inbiqeba.galk.map.sources;

import com.inbiqeba.galk.map.Source;

public class MapQuestSource implements TileSource
{
  public static final int TYPE_SAT = 0;

  private int type;

  public MapQuestSource(int type)
  {
    this.type = type;
  }

  @Override
  public String toJavaScript()
  {
    String typeStr;
    switch(type) {
      default:
        typeStr = "sat";
        break;
    }

    return "new ol.source.MapQuest({layer: '" + typeStr + "'})";
  }
}
