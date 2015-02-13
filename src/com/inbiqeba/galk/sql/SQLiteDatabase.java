package com.inbiqeba.galk.sql;

import org.sqlite.SQLiteConfig;
import javax.transaction.TransactionRequiredException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabase implements SQLDatabase
{
  private String databaseName;

  private class SQLiteTransaction extends SQLTransaction
  {
    @Override
    public boolean start()
    {
      SQLiteConfig config;
      try {
        Class.forName("org.sqlite.JDBC");
        config = new SQLiteConfig();
        config.setSharedCache(false);
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db", config.toProperties());
        executeUpdate("BEGIN TRANSACTION;");
      } catch (SQLException e) {
        e.printStackTrace();
        return false;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return false;
      }
      return true;
    }

    @Override
    public boolean commit()
    {
      return  executeUpdate("END TRANSACTION;");
    }

    @Override
    public boolean rollback()
    {
      return true;
    }
  }

  public SQLiteDatabase(String databaseName)
  {
    this.databaseName = databaseName;
  }

  @Override
  public boolean initialize()
  {
    return true;
  }

  @Override
  public boolean update(int currentVersion)
  {
    return true;
  }

  @Override
  public SQLTransaction createNewTransaction()
  {
    return new SQLiteTransaction();
  }
}
