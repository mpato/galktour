package com.inbiqeba.galk.sql;

import com.inbiqeba.galk.data.SetConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public abstract class SQLTable<T extends SQLObject>
{

  public abstract String getTableName();
  public abstract T createEmptyRecord();
  public abstract String getWhereClause();
  public abstract String getCreateQuery();
  public abstract Vector<T> toVector();
  public abstract String getUpdateQuery(T record);
  public abstract String getInsertQuery(T record);
  public abstract void fromSQLResult(T record, ResultSet result);
  protected abstract int getRecordId(T record);

  public boolean insertNewRecord(SQLTransaction trans, T record)
  {
    if (trans == null)
      return false;
    return trans.executeInsert(getInsertQuery(record));
  }

  public void map(SQLTransaction trans, SetConverter<T> func)
  {
    ResultSet rs;
    String whereClause, query;
    T record;
    if (trans == null)
      return;
    whereClause = getWhereClause();
    whereClause = whereClause != null && !whereClause.trim().isEmpty()? "where " + whereClause : "";
    query = String.format("select * from %s %s", getTableName(), whereClause);
    rs = trans.executeQuery(query);
    try {
      while (rs.next()) {
        record = createEmptyRecord();
        fromSQLResult(record, rs);
        func.convertSetElement(getRecordTag(record), record);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  protected String getRecordTag(T record)
  {
    return getTableName() + "::" + getRecordId(record);
  }
}
