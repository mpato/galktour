package com.inbiqeba.galk;

import java.util.Vector;

public interface DataSet<T>
{
  public String getCreateQuery();
  public boolean insertNewRecord(T record);
  public Vector<T> toVector();
  public void map(SetConverter<T> func);
}
