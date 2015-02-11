package com.inbiqeba.galk.map.sources;

import com.inbiqeba.galk.map.Feature;
import com.inbiqeba.galk.sql.SQLSetConverter;

public class FeatureSourceConverter implements SQLSetConverter<Feature>
{
  private FeatureSource source;

  public FeatureSourceConverter(FeatureSource source)
  {
    this.source = source;
  }

  public void convertSetElement(Feature element)
  {
    source.addFeature(element);
  }
}
