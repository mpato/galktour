package com.inbiqeba.galk.sql;

import com.inbiqeba.galk.data.SetConverter;
import java.sql.PreparedStatement;
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
  public abstract SQLObjectQuery<T> getUpdateQuery(SQLTransaction trans);
  public abstract SQLObjectQuery<T> getInsertQuery(SQLTransaction trans);
  public abstract void fromSQLResult(T record, ResultSet result);
  protected abstract int getRecordId(T record);

  public boolean insertNewRecord(SQLTransaction trans, T record)
  {
    SQLObjectQuery<T> query;
    if (trans == null)
      return false;
    query = getInsertQuery(trans);
    query.instantiate(record);
    return query.execute();
  }

  protected String getRecordTag(T record)
  {
    return getTableName() + "::" + getRecordId(record);
  }

  public SQLView<T> getView(SQLTransaction trans)
  {
    String whereClause;
    whereClause = getWhereClause();
    whereClause = whereClause != null && !whereClause.trim().isEmpty()? "where " + whereClause : "";
    return new SQLView<T>(this, trans.getPreparedStatement(String.format("select * from %s %s", getTableName(), whereClause)));
  }

  public void map(SQLTransaction trans, SetConverter<T> func)
  {
    getView(trans).map(func);
  }
}
