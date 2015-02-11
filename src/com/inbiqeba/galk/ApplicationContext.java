package com.inbiqeba.galk;

import java.util.HashMap;

public class ApplicationContext
{
  protected static boolean debug;
  protected static DataSource dataSource;
  private static HashMap<String, DataSet> dataSets;

  public static void addDataSet(String name, DataSet dataSet)
  {
    if (dataSet == null)
      return;
    if (dataSets == null)
      dataSets = new HashMap<String, DataSet>();
    dataSets.put(name, dataSet);
    dataSource.executeCreate(dataSet.getCreateQuery());
  }


  public static boolean isDebug()
  {
    return debug;
  }
}
