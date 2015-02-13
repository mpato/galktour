package com.inbiqeba.galk.core;

import com.inbiqeba.galk.html.map.Feature;
import com.inbiqeba.galk.sql.SQLDatabase;
import com.inbiqeba.galk.sql.SQLTable;
import com.inbiqeba.galk.data.PointOfInterest;

public class ApplicationContext
{
  private static boolean sealed;
  private static boolean debug;
  private static SQLDatabase dataSource;
  protected static SQLTable<PointOfInterest> pointsOfInterest;


  public static boolean isDebug()
  {
    return debug;
  }

  public static SQLDatabase getDataSource()
  {
    return dataSource;
  }

  public static void setDebug(boolean debug)
  {
    if (sealed)
      return;
    ApplicationContext.debug = debug;
  }

  public static void setDataSource(SQLDatabase dataSource)
  {
    if (sealed)
      return;
    ApplicationContext.dataSource = dataSource;
  }

  public static void seal()
  {
    ApplicationContext.sealed = true;
  }

  public static SQLTable<PointOfInterest> getPointsOfInterest()
  {
    return pointsOfInterest;
  }
}
