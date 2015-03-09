package com.inbiqeba.galk.gui;

import com.inbiqeba.galk.core.utils.ByteBuffer;

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

  @Override
  public void toByteBuffer(ByteBuffer buffer)
  {
    buffer.put(toHTML());
  }
}
