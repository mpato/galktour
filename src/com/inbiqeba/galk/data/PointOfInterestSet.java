package com.inbiqeba.galk.data;

import com.inbiqeba.galk.sql.SQLObjectQuery;
import com.inbiqeba.galk.sql.SQLTable;
import com.inbiqeba.galk.sql.SQLTransaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PointOfInterestSet extends SQLTable<PointOfInterest>
{
  class POIInsertQuery extends SQLObjectQuery<PointOfInterest>
  {
    POIInsertQuery(SQLTransaction trans)
    {
      statement = trans.getPreparedStatement("insert into " + PointOfInterestSet.getName() + " values(?, ?, ?, ?, ?, ?)");
    }

    @Override
    public void instantiate(PointOfInterest record)
    {
      try {
        statement.setInt(1, record.id);
        statement.setString(2, record.description);
        statement.setDouble(3, record.x);
        statement.setDouble(4, record.y);
        statement.setInt(5, record.type);
        statement.setInt(6, record.parentId);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

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

  @Override
  public SQLObjectQuery<PointOfInterest> getUpdateQuery(SQLTransaction trans)
  {
    return null;
  }

  @Override
  public SQLObjectQuery<PointOfInterest> getInsertQuery(SQLTransaction trans)
  {
    return new POIInsertQuery(trans);
  }

  @Override
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
