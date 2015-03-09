package com.inbiqeba.galk.core.utils.template;

import java.util.Map;

public class VariableMap<T>
{
  private Map<String, T> map;

  public VariableMap(Map<String, T> map)
  {
    this.map = map;
  }

  public int size()
  {
    return map.size();
  }

  public boolean isEmpty()
  {
    return map.isEmpty();
  }

  public boolean containsKey(String key)
  {
    return map.containsKey(key);
  }

  public T get(String key)
  {
    return map.get(key);
  }
}
