package com.inbiqeba.galk.html;

import com.inbiqeba.galk.core.utils.ByteBuffer;

public class HTMLEmpty implements HTMLComponent
{
  @Override
  public String getHTMLHeader()
  {
    return "";
  }

  @Override
  public String toHTML()
  {
    return "";
  }

  @Override
  public void toByteBuffer(ByteBuffer buffer)
  {
  }
}
