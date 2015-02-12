package com.inbiqeba.galk;

import com.inbiqeba.galk.html.map.Feature;
import com.inbiqeba.galk.sql.SQLDatabase;
import com.inbiqeba.galk.sql.SQLTable;
import java.util.Vector;

public class FeatureDataSet extends SQLTable<Feature>
{
  @Override
  public String getCreateQuery()
  {
    return "create table " + getTableName() + " (id integer PRIMARY KEY, name string, point_x double, point_y double)";
  }

  @Override
  public Vector<Feature> toVector()
  {
    return new Vector<Feature>();
  }
  @Override
  public String getTableName()
  {
    return getName();
  }

  @Override
  public Feature createEmptyRecord()
  {
    return new Feature(null, "", 0);
  }

  @Override
  public SQLDatabase getSQLDatabase()
  {
    if (ApplicationContext.dataSource instanceof SQLDatabase)
      return (SQLDatabase)ApplicationContext.dataSource;
    return null;
  }

  @Override
  public String getWhereClause()
  {
    return null;
  }

  public static String getName()
  {
    return "feature";
  }
}
