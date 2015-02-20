package com.inbiqeba.galk.sql;

import com.inbiqeba.galk.data.SetConverter;
import java.sql.*;

public abstract class SQLTransaction
{
  protected Connection connection;

  public abstract boolean start();
  public abstract boolean commit();
  public abstract boolean rollback();

  public ResultSet executeQuery(String query)
  {
    Statement statement;

    try {
      statement = connection.createStatement();
      statement.setQueryTimeout(30);// set timeout to 30 sec
      return statement.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    // statement.setQueryTimeout(30);  // set timeout to 30 sec.

      /*statement.executeUpdate("drop table if exists person");
      statement.executeUpdate("create table person (id integer, name string)");
      statement.executeUpdate("insert into person values(1, 'leo')");
      statement.executeUpdate("insert into person values(2, 'yui')");
      ResultSet rs =
    return null;*/
  }

  public boolean executeUpdate(String query)
  {
    Statement statement;
    try {
      statement = connection.createStatement();
      statement.setQueryTimeout(30);// set timeout to 30 sec
      statement.executeUpdate(query);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }

  }

  public boolean executeCreate(String query)
  {
    return executeUpdate(query);
  }

  public boolean executeInsert(String query)
  {
    return executeUpdate(query);
  }

  public PreparedStatement getPreparedStatement(String query)
  {
    try {
      return connection.prepareStatement(query);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}
