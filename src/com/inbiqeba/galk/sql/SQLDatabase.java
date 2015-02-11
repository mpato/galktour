package com.inbiqeba.galk.sql;

import com.inbiqeba.galk.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class SQLDatabase implements DataSource
{
  public Connection connection;

  @Override
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

  @Override
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

  @Override
  public boolean executeCreate(String query)
  {
    return executeUpdate(query);
  }

  @Override
  public boolean executeInsert(String query)
  {
    return executeUpdate(query);
  }
}
