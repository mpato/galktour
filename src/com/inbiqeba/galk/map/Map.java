package com.inbiqeba.galk.map;

import com.inbiqeba.galk.gui.Length;
import com.inbiqeba.galk.html.HtmlComponent;
import java.util.Vector;

public class Map implements HtmlComponent
{
  private Vector<Layer> layers;
  private View view;
  private Length width, height;

  public Map(View view, Length width, Length height)
  {
    this.view = view;
    this.width = width;
    this.height = height;
    this.layers = new Vector<Layer>();
  }

  @Override
  public String getHTMLHeader()
  {
    return "<link rel=\"stylesheet\" href=\"http://openlayers.org/en/v3.2.0/css/ol.css\" type=\"text/css\">" +
           "\n<style>" +
           "\n .map {" +
           "\n       height: " + height.toHTML() + ";" +
           "\n       width: " + width.toHTML() + ";" +
           "\n      }" +
           "\n</style>"+
           "\n<script src=\"http://openlayers.org/en/v3.2.0/build/ol.js\" type=\"text/javascript\"></script>";
  }


  private String layersToJavaScript()
  {
    String ret;
    int i = 0;
    if (layers.size() == 0)
      return "nil";
    ret = "[";
    for (Layer layer: layers) {
      if (i != 0)
        ret += ", ";
      ret += layer.toJavaScript();
      i++;
    }
    ret += "]";
    return ret;
  }


  @Override
  public String toHTML()
  {
    return "<div id='map' class='map'></div>" +
           "\n<script type=\"text/javascript\">" +
           "\n      var map = new ol.Map({" +
           "\n        target: 'map', " +
           "\n        layers:" + layersToJavaScript() + ", " +
           "\n        view: " + view.toJavaScript() +
           "\n      });" +
           "\n</script>";
  }

  public void addLayer(Layer layer)
  {
    layers.add(layer);
  }
}
