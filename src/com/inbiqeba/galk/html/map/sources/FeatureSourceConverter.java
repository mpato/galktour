package com.inbiqeba.galk.html.map.sources;

import com.inbiqeba.galk.data.PointOfInterest;
import com.inbiqeba.galk.gui.geometry.GeometryPoint;
import com.inbiqeba.galk.html.map.Feature;
import com.inbiqeba.galk.sql.SQLSetConverter;

public class FeatureSourceConverter implements SQLSetConverter<PointOfInterest>
{
  private FeatureSource source;

  public FeatureSourceConverter(FeatureSource source)
  {
    this.source = source;
  }

  @Override
  public void convertSetElement(String tag, PointOfInterest element)
  {
    Feature feature;
    feature = new Feature(new GeometryPoint(element.x, element.y), element.description, element.id);
    feature.setTag(tag);
    source.addFeature(feature);
  }
}
