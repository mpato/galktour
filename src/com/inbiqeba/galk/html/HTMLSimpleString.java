package com.inbiqeba.galk.html;

import com.inbiqeba.galk.core.utils.ByteBuffer;

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

  @Override
  public void toByteBuffer(ByteBuffer buffer)
  {
    buffer.put(text);
  }
}
