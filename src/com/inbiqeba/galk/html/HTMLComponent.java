package com.inbiqeba.galk.html;

import com.inbiqeba.galk.core.utils.ByteObject;

public interface HTMLComponent extends ByteObject
{
  public String getHTMLHeader();
  public String toHTML();
}
