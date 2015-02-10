package com.inbiqeba.galk.gui;


public class RelativeLength implements Length
{
  private int value;

  public RelativeLength(int value)
  {
    this.value = value;
  }

  @Override
  public String getHTMLHeader()
  {
    return "";
  }

  @Override
  public String toHTML()
  {
    return value + "%";
  }
}
