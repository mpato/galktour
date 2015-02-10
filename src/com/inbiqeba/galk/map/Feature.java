package com.inbiqeba.galk.map;

import com.inbiqeba.galk.gui.geometry.GeometryPoint;
import com.inbiqeba.galk.html.JavaScriptComponent;

public class Feature implements JavaScriptComponent
{
  private GeometryPoint geometry;
  private String name;

  /*public Feature(GeometryPoint geometry, String name)
  {
    this.geometry = geometry;
    this.name = name;
  }*/

  @Override
  public String toJavaScript()
  {
    String style;
    style = "new ol.style.Style({ image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({anchor: [0.5, 46], anchorXUnits: 'fraction'," +
            "anchorYUnits: 'pixels',opacity: 0.75,src: 'http://openlayers.org/en/v3.2.0/examples/data/icon.png'}))})";

    return "new ol.Feature({geometry: new ol.geom.Point([0, 0]),name: 'Null Island',population: 4000,rainfall: 500, style:" + style + "})";
  }
}
