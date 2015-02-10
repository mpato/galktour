package com.inbiqeba.galk.map.sources;

import com.inbiqeba.galk.map.Feature;
import java.util.Vector;

public class FeatureSource implements VectorSource
{
  Vector<Feature> features;

  public FeatureSource()
  {
    features = new Vector<Feature>();
  }

  public void addFeature(Feature feature)
  {
    features.add(feature);
  }

  public String featuresToJavaScript()
  {
    String ret;
    int i = 0;
    if (features.size() == 0)
      return "nil";
    ret = "[";
    for (Feature feature: features) {
      if (i != 0)
        ret += ", ";
      ret += feature.toJavaScript();
      i++;
    }
    ret += "]";
    return ret;
  }

  @Override
  public String toJavaScript()
  {
    return "new ol.source.Vector({features: " + featuresToJavaScript() + "})";
  }
}
