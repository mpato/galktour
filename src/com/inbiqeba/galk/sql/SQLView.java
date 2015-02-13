package com.inbiqeba.galk.sql;

import com.inbiqeba.galk.data.SetConverter;
import com.inbiqeba.galk.data.View;
import java.sql.ResultSet;
import java.util.Vector;

public class SQLView<T extends SQLObject> implements View<T>
{
  private SQLTable<T> source;
  private SQLTransaction transaction;

  public SQLView(SQLTable<T> source, SQLTransaction transaction)
  {
    this.source = source;
    this.transaction = transaction;
  }

  @Override
  public void map(SetConverter<T> func)
  {
    source.map(transaction, func);
  }
}
