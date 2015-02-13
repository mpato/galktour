package com.inbiqeba.galk.sql;

public interface SQLDatabase
{
  public boolean initialize();
  public boolean update(int currentVersion);
  public SQLTransaction createNewTransaction();
}
