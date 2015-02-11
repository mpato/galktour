package com.inbiqeba.galk.sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabase extends SQLDatabase
{
  private String databaseName;

  public SQLiteDatabase(String databaseName)
  {
    this.databaseName = databaseName;
  }

  @Override
  public boolean initialize()
  {
    try
    {
      //Statement statement;
      Class.forName("org.sqlite.JDBC");

      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
      // statement = connection.createStatement();
     // statement.setQueryTimeout(30);  // set timeout to 30 sec.

      /*statement.executeUpdate("drop table if exists person");
      statement.executeUpdate("create table person (id integer, name string)");
      statement.executeUpdate("insert into person values(1, 'leo')");
      statement.executeUpdate("insert into person values(2, 'yui')");
      ResultSet rs = statement.executeQuery("select * from person");
      while(rs.next())
      {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }*/
    } catch(SQLException e)
    {
      e.printStackTrace();
      return false;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean update(int currentVersion)
  {
    return true;
  }
}
