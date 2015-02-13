package com.inbiqeba.galk.data;

public interface View<T>
{
  public void map(SetConverter<T> func);
}
