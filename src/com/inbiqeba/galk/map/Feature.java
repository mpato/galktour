package com.inbiqeba.galk.map;

import com.inbiqeba.galk.FeatureDataSet;
import com.inbiqeba.galk.gui.geometry.GeometryPoint;
import com.inbiqeba.galk.html.JavaScriptComponent;
import com.inbiqeba.galk.html.JavaScriptSnippet;
import com.inbiqeba.galk.sql.SQLObject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Feature implements JavaScriptComponent, SQLObject
{
  private GeometryPoint geometry;
  private String name;
  private int id;

  public Feature(GeometryPoint geometry, String name, int id)
  {
    this.geometry = geometry;
    this.name = name;
    this.id = id;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    JavaScriptSnippet snippet;
    snippet = new JavaScriptSnippet();
    snippet.addInitialization("style_" + id + " = new ol.style.Style({ image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({anchor: [" + (0.5 * 1) + ", 46], anchorXUnits: 'fraction'," +
                              "anchorYUnits: 'pixels',opacity: 0.75,src: 'http://openlayers.org/en/v3.2.0/examples/data/icon.png'}))})");

    snippet.addInitialization(String.format("\nicon_" + id  + " = new ol.Feature({geometry: new ol.geom.Point([%.4f, %.4f]),name: '%s',population: 4000,rainfall: 500})", geometry.getX() * 100000, geometry.getY() * 100000, name));
    snippet.addInitialization("\nicon_" + id + ".setStyle(style_" + id + ");");
    snippet.add("icon_" + id);
    return snippet;
  }

  @Override
  public String getUpdateQuery()
  {
    return null;
  }

  @Override
  public String getInsertQuery()
  {
    return String.format("insert into %s values(%d, '%s', %.4f, %.4f)", FeatureDataSet.getName(), id, name, geometry.getX(), geometry.getY());
  }

  @Override
  public void fromSQLResult(ResultSet result)
  {
    try {
      id = result.getInt("id");
      geometry = new GeometryPoint(result.getDouble("point_x"), result.getDouble("point_y"));
      name = result.getString("name");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
