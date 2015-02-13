package com.inbiqeba.galk.sql;

import java.sql.ResultSet;
import java.util.Vector;

public class SQLFilteredTable<T extends SQLObject> extends SQLTable<T>
{
  public static final int OP_EQUAL = 0;
  public static final int OP_NOT = -1;
  public static final int OP_GT =  1;
  public static final int OP_GTE = 2;
  public static final int OP_LS =  3;
  public static final int OP_LSE = 4;
  private SQLTable<T> source;
  private String whereClause;

  public SQLFilteredTable(SQLTable<T> source)
  {
    this.source = source;
    this.whereClause = "";
  }

  @Override
  public String getTableName()
  {
    return source.getTableName();
  }

  @Override
  public T createEmptyRecord()
  {
    return source.createEmptyRecord();
  }

  @Override
  public String getWhereClause()
  {
    return whereClause;
  }

  @Override
  public String getCreateQuery()
  {
    return source.getCreateQuery();
  }

  @Override
  public Vector<T> toVector()
  {
    return source.toVector();
  }

  @Override
  public String getUpdateQuery(T record)
  {
    return source.getUpdateQuery(record);
  }

  @Override
  public String getInsertQuery(T record)
  {
    return source.getInsertQuery(record);
  }

  @Override
  public void fromSQLResult(T record, ResultSet result)
  {
    source.fromSQLResult(record, result);
  }

  @Override
  protected int getRecordId(T record)
  {
    return source.getRecordId(record);
  }

  private void addToClause(String condition)
  {
    if (!whereClause.isEmpty())
      whereClause += ",";
    whereClause += condition;
  }

  public SQLFilteredTable<T> filter(String field, int op, String value, boolean escape)
  {
    String opStr;
    switch (op) {
      case OP_GT:
        opStr = ">";
        break;
      case OP_GTE:
        opStr = ">=";
        break;
      case OP_LS:
        opStr = "<";
        break;
      case OP_LSE:
        opStr = ">";
        break;
      default:
        opStr = "=";
        break;
    }
    if (escape)
      value = String.format("'%s'", value.replace("'","\'"));
    addToClause(String.format("%s %s %s", field, opStr, value));
    return this;
  }

  public SQLFilteredTable<T> filter(String field, int op, int value)
  {
     return filter(field, op, String.valueOf(value), false);
  }

  public SQLFilteredTable<T> filter(String field, int op, double value)
  {
    return filter(field, op, String.valueOf(value), false);
  }

  public SQLFilteredTable<T> filter(String field, int op, String value)
  {
    return filter(field, op, value, true);
  }

  public SQLFilteredTable<T> filterField(String field, int op, String field2)
  {
    return filter(field, op, field2, false);
  }
}
