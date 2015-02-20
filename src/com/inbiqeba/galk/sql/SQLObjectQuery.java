package com.inbiqeba.galk.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SQLObjectQuery<T>
{
  protected PreparedStatement statement;
  public abstract void instantiate(T data);

  public boolean execute()
  {
    if (statement == null)
      return false;
    try {
      return statement.executeUpdate() >= 1;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
