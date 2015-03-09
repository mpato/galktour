package com.inbiqeba.galk.core.data;

public interface View<T>
{
  public void map(SetConverter<T> func);
}
