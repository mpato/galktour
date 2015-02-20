package com.inbiqeba.galk.web;

import com.inbiqeba.galk.core.screen.MainMapScreen;
import com.inbiqeba.galk.gui.RelativeLength;
import com.inbiqeba.galk.html.HTMLForm;
import com.inbiqeba.galk.html.HTMLComponent;
import com.inbiqeba.galk.html.map.Map;
import com.inbiqeba.galk.html.map.View;
import com.inbiqeba.galk.html.map.coordinates.PlainCoordinates;
import com.inbiqeba.galk.html.map.layers.TileLayer;
import com.inbiqeba.galk.html.map.layers.VectorLayer;
import com.inbiqeba.galk.html.map.sources.FeatureSource;
import com.inbiqeba.galk.html.map.sources.FeatureSourceConverter;
import com.inbiqeba.galk.html.map.sources.MapQuestSource;
import com.inbiqeba.galk.html.page.TemplatePage;
import java.util.HashMap;

public class MapPage extends TemplatePage implements HTMLComponent
{
  private String title;
  private Map map;
  private MainMapScreen screen;
  private HTMLForm form;
  private String contextID;

  public MapPage()
  {
    this.title = "Title Map";
    this.templateName = "main_map";
  }

  public void initialize()
  {
    this.form = new HTMLForm("", HTMLForm.METHOD_POST);
    this.screen = new MainMapScreen();
    this.form.add("test", HTMLForm.INPUT_TYPE_TEXT, "");
    this.form.add("contextID", HTMLForm.INPUT_TYPE_HIDDEN, contextID);
    this.form.add("submit", HTMLForm.INPUT_TYPE_SUBMIT, "Submit me!");
    initializeMap();
  }

  private void initializeMap()
  {
    FeatureSource features;
    FeatureSourceConverter converter;
    map = new Map(new View(new PlainCoordinates(0, 0), 4), new RelativeLength(100), new RelativeLength(100));
    features = new FeatureSource();
    converter = new FeatureSourceConverter(features);
    screen.getPointsOfInterest().map(converter);
    //features.addFeature(new Feature());
    //map.addLayer(new TileLayer(new TileJSON("http://api.tiles.mapbox.com/v3/mapbox.geography-class.jsonp", "")));
    map.addLayer(new TileLayer(new MapQuestSource(MapQuestSource.TYPE_OSM)));
    map.addLayer(new VectorLayer(features));
    addTemplateVariable("MAP", map);
    addTemplateVariable("INFO", form);
  }

  @Override
  public String getHTMLHeader()
  {
    return "\n<script src=\"resources/utils.js\" type=\"text/javascript\"></script>"
           + "\n<script type=\"text/javascript\">context_id='" + getContextID() + "'</script>"
           + "\n<title>" + title + "</title>"
           + "\n" + super.getHTMLHeader();
  }

  @Override
  public void setContextID(String tag)
  {
    this.contextID = tag;
  }

  @Override
  public String getContextID()
  {
    return contextID;
  }

  @Override
  public void setFormValues(HashMap<String, String> values)
  {
    form.setFormValues(values);
  }
}
