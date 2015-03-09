package com.inbiqeba.galk.html;

import com.inbiqeba.galk.core.utils.ByteBuffer;
import java.util.Iterator;
import java.util.Vector;

public class HTMLCollection<T extends HTMLComponent> implements Iterable<T>, HTMLComponent
{
  Vector<T> components;

  public HTMLCollection()
  {
    components = new Vector<T>();
  }

  public void add(T component)
  {
    components.add(component);
  }

  @Override
  public Iterator<T> iterator()
  {
    return components.iterator();
  }

  @Override
  public String getHTMLHeader()
  {
    String ret;
    ret = "";
    for (HTMLComponent component : components)
      ret += component.getHTMLHeader();
    return ret;
  }

  @Override
  public String toHTML()
  {
    String ret;
    ret = "";
    for (HTMLComponent component : components)
      ret += component.toHTML();
    return ret;
  }

  @Override
  public void toByteBuffer(ByteBuffer buffer)
  {
    for (HTMLComponent component : components)
      component.toByteBuffer(buffer);
  }
}
