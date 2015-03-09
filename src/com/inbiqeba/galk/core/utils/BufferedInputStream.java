package com.inbiqeba.galk.core.utils;

import java.io.InputStream;

public class BufferedInputStream extends java.io.BufferedInputStream
{
  public BufferedInputStream(InputStream in)
  {
    super(in);
  }

  public BufferedInputStream(InputStream in, int size)
  {
    super(in, size);
  }

  public void copyToBuffer(ByteBuffer buffer, int offset, int length)
  {
    if (offset < 0 || length < 0)
      return;
    buffer.put(buf, offset, length);
  }

  public boolean bufferIsFull()
  {
    return pos >= count;
  }

  public int getPosition()
  {
    return pos;
  }
}
