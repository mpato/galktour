package com.inbiqeba.galk.html;

import java.util.Iterator;
import java.util.Vector;

public class HTMLCollection implements Iterable<HTMLComponent>, HTMLComponent
{
  Vector<HTMLComponent> components;

  public HTMLCollection()
  {
    components = new Vector<HTMLComponent>();
  }

  public void add(HTMLComponent component)
  {
    components.add(component);
  }

  @Override
  public Iterator<HTMLComponent> iterator()
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
}
