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
  public abstract String getWhereClause();

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
    String whereClause, query;
    T record;
    database = getSQLDatabase();
    if (database == null)
      return;
    whereClause = getWhereClause();
    whereClause = whereClause != null && !whereClause.trim().isEmpty()? "where " + whereClause : "";
    query = String.format("select * from %s %s", getTableName(), whereClause);
    rs = database.executeQuery(query);
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
