package com.inbiqeba.galk.gui;

import com.inbiqeba.galk.core.utils.ByteBuffer;

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

  @Override
  public void toByteBuffer(ByteBuffer buffer)
  {
    buffer.put(toHTML());
  }
}
