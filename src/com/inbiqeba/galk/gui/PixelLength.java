package com.inbiqeba.galk.gui;

public class PixelLength implements Length
{
  private int value;

  public PixelLength(int value)
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
    return value + "px";
  }
}
