package com.inbiqeba.galk.core.utils;

public class DiscardByteBuffer extends ByteBuffer
{
  public DiscardByteBuffer()
  {
    super(0);
  }

  @Override
  public void put(byte[] source, int offset, int length)
  {
  }

  @Override
  public String toString()
  {
    return "";
  }
}
