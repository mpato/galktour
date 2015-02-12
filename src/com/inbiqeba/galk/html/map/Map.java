package com.inbiqeba.galk.html.map;

import com.inbiqeba.galk.gui.Length;
import com.inbiqeba.galk.html.HtmlComponent;
import com.inbiqeba.galk.html.JavaScriptComponent;
import com.inbiqeba.galk.html.JavaScriptSnippet;
import java.util.Vector;

public class Map implements HtmlComponent, JavaScriptComponent
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


  private JavaScriptSnippet layersToJavaScript()
  {
    JavaScriptSnippet ret;
    int i = 0;
    if (layers.size() == 0)
      return new JavaScriptSnippet("nil");
    ret = new JavaScriptSnippet();
    ret.add("[");
    for (Layer layer: layers) {
      if (i != 0)
        ret.add(", ");
      ret.add(layer.toJavaScript());
      i++;
    }
    ret.add("]");
    return ret;
  }

  @Override
  public JavaScriptSnippet toJavaScript()
  {
    JavaScriptSnippet snippet;
    snippet = new JavaScriptSnippet();
    snippet.add("\n      var map = new ol.Map({" +
    //            "\n   renderer: exampleNS.getRendererFromQueryString()," +
    "\n        target: 'map', " +
    "\n        layers:");
    snippet.add(layersToJavaScript());
    snippet.add(", " +
    "\n        view: ");
    snippet.add(view.toJavaScript());
    snippet.add("\n      });");
    return snippet;
  }

  @Override
  public String toHTML()
  {
    return "<div id='map' class='map'></div>" +
           "\n<script type=\"text/javascript\">" +
           "\n" + toJavaScript() +
           "\n</script>";
  }

  public void addLayer(Layer layer)
  {
    layers.add(layer);
  }
}
