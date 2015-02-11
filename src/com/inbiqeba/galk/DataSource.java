package com.inbiqeba.galk;

import java.sql.ResultSet;

public interface DataSource
{
  public boolean initialize();
  public boolean update(int currentVersion);
  public ResultSet executeQuery(String query);
  public boolean executeCreate(String query);
  public boolean executeInsert(String query);
  public boolean executeUpdate(String query);
}
