package com.inbiqeba.galk.sql;

import com.inbiqeba.galk.DataSet;
import com.inbiqeba.galk.SetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SQLTable<T extends SQLObject> implements DataSet<T>
{

  public abstract String getTableName();
  public abstract T createEmptyRecord();
  public abstract SQLDatabase getSQLDatabase();

  @Override
  public boolean insertNewRecord(T record)
  {
    SQLDatabase database;
    database = getSQLDatabase();
    if (database == null)
      return false;
    return database.executeInsert(record.getInsertQuery());
  }

  @Override
  public void map(SetConverter<T> func)
  {
    SQLDatabase database;
    ResultSet rs;
    T record;
    database = getSQLDatabase();
    if (database == null)
      return;
    rs = database.executeQuery("select * from " + getTableName());
    try {
      while(rs.next())
      {
        record = createEmptyRecord();
        record.fromSQLResult(rs);
        func.convertSetElement(record);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
