package com.inbiqeba.galk.html;


public class HTMLSimpleString implements HTMLComponent
{
  public String text;

  public HTMLSimpleString(String text)
  {
    this.text = text;
  }

  @Override
  public String getHTMLHeader()
  {
    return "";
  }

  @Override
  public String toHTML()
  {
    return text;
  }
}
