package com.inbiqeba.galk.sql;

import com.inbiqeba.galk.core.data.SetConverter;
import com.inbiqeba.galk.core.data.View;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLView<T extends SQLObject> implements View<T>
{
  private SQLTable<T> source;
  //private SQLTransaction transaction;
  private PreparedStatement statement;

  public SQLView(SQLTable<T> source, PreparedStatement statement)
  {
    this.source = source;
    this.statement = statement;
    //this.transaction = transaction;
  }

  @Override
  public void map(SetConverter<T> func)
  {
    ResultSet rs;
    T record;
    try {
      rs = statement.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
      return;
    }
    try {
      while (rs.next()) {
        record = source.createEmptyRecord();
        source.fromSQLResult(record, rs);
        func.convertSetElement(source.getRecordTag(record), record);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
