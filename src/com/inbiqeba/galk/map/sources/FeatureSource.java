package com.inbiqeba.galk.map.sources;

import com.inbiqeba.galk.html.JavaScriptSnippet;
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

  public JavaScriptSnippet featuresToJavaScript()
  {
    JavaScriptSnippet ret;
    int i = 0;
    if (features.size() == 0)
      return new JavaScriptSnippet("nil");
    ret = new JavaScriptSnippet();
    ret.add("[");
    for (Feature feature: features) {
      if (i != 0)
        ret.add(", ");
      ret.add(feature.toJavaScript());
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
    snippet.add("new ol.source.Vector({features: ");
    snippet.add(featuresToJavaScript());
    snippet.add("})");
    return snippet ;
  }
}
