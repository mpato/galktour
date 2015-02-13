package com.inbiqeba.galk.data;

import com.inbiqeba.galk.core.ApplicationContext;
import com.inbiqeba.galk.sql.SQLDatabase;
import com.inbiqeba.galk.sql.SQLTable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PointOfInterestSet extends SQLTable<PointOfInterest>
{
  @Override
  public String getCreateQuery()
  {
    return "create table " + getTableName() + " (id integer PRIMARY KEY, description string, point_x double, point_y double, type int, parent_id int)";
  }

  @Override
  public Vector<PointOfInterest> toVector()
  {
    return new Vector<PointOfInterest>();
  }

  public String getUpdateQuery(PointOfInterest record)
  {
    return null;
  }

  public String getInsertQuery(PointOfInterest record)
  {
    return String.format("insert into %s values(%d, '%s', %.4f, %.4f, %d, %d)", PointOfInterestSet.getName(), record.id, record.description, record.x, record.y, record.type, record.parentId);
  }

  public void fromSQLResult(PointOfInterest record, ResultSet result)
  {
    try {
      record.id = result.getInt("id");
      record.x = result.getDouble("point_x");
      record.y = result.getDouble("point_y");
      record.description = result.getString("description");
      record.type = result.getInt("type");
      record.parentId = result.getInt("parent_id");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected int getRecordId(PointOfInterest record)
  {
    return record.id;
  }

  @Override
  public String getTableName()
  {
    return getName();
  }

  @Override
  public PointOfInterest createEmptyRecord()
  {
    return new PointOfInterest();
  }

  @Override
  public String getWhereClause()
  {
    return null;
  }

  public static String getName()
  {
    return "point_of_interest";
  }
}
