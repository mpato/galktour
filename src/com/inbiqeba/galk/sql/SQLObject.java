package com.inbiqeba.galk.sql;

import java.sql.ResultSet;

public interface SQLObject
{
  public String getUpdateQuery();
  public String getInsertQuery();
  public void fromSQLResult(ResultSet result);
}
