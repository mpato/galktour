package com.inbiqeba.galk.core.utils;

public class ByteBuffer
{
  private byte buffer[];
  private int capacity, pos;

  public ByteBuffer(int capacity)
  {
    this.buffer = new byte[capacity];
    this.pos = 0;
    resize(capacity);
  }

  public ByteBuffer()
  {
    this(128);
  }

  public  void clear()
  {
    pos = 0;
  }

  private void resize(int newCapacity)
  {
    byte oldBuffer[];
    oldBuffer = buffer;
    capacity = newCapacity;
    capacity = (int)(Math.ceil(capacity / 2048.0)) * 2048;
    buffer = new byte[capacity];
    if (oldBuffer != null)
      System.arraycopy(oldBuffer, 0, buffer, 0, oldBuffer.length);
  }

  public void put(byte source[], int offset, int length)
  {
    if (source == null || offset >= source.length)
      return;
    if (offset + length > source.length)
      length = source.length - offset;
    if (capacity < pos + length)
      resize(pos + length);
    System.arraycopy(source, offset, buffer, pos, length);
    pos += length;
  }

  public void put(String str)
  {
    byte buf[];
    buf = str.getBytes();
    put(buf, 0, buf.length);
  }

  @Override
  public String toString()
  {
    return new String(buffer, 0, pos);
  }

  public int getPosition()
  {
    return pos;
  }
}
