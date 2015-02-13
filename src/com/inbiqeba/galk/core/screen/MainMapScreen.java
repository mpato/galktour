package com.inbiqeba.galk.core.screen;

import com.inbiqeba.galk.core.ApplicationContext;
import com.inbiqeba.galk.data.View;
import com.inbiqeba.galk.sql.SQLFilteredTable;
import com.inbiqeba.galk.sql.SQLTransaction;
import com.inbiqeba.galk.sql.SQLView;
import com.inbiqeba.galk.sql.SQLTable;
import com.inbiqeba.galk.data.PointOfInterest;
import java.util.HashMap;
import java.util.UUID;

public class MainMapScreen implements Screen
{
  public static final int STATE_SHOW_MAP = 0;

  private class MainMapContext implements Context
  {
    int state;
    SQLTransaction transaction;
  }

  private SQLTable<PointOfInterest> mainLocations;
  private HashMap<String, MainMapContext> contexts;

  public MainMapScreen()
  {
    this.contexts = new HashMap<String, MainMapContext>();
    this.mainLocations = new SQLFilteredTable<PointOfInterest>(ApplicationContext.getPointsOfInterest())
                           .filter("type", SQLFilteredTable.OP_EQUAL, PointOfInterest.TYPE_EMPTY);
  }

  @Override
  public String registerNewContext()
  {
    MainMapContext newContext;
    String tag;
    tag = UUID.randomUUID().toString();
    newContext = new MainMapContext();
    newContext.state = STATE_SHOW_MAP;
    newContext.transaction = ApplicationContext.getDataSource().createNewTransaction();
    newContext.transaction.start();
    contexts.put(tag, newContext);
    return tag;
  }

  @Override
  public void discardContext(String contextID)
  {
  }

  @Override
  public boolean commitContext(String contextID)
  {
    return false;
  }

  public View<PointOfInterest> getPointsOfInterest(String contextID)
  {
    MainMapContext context;
    context = contexts.get(contextID);
    if (context == null)
      return null;

    return new SQLView<PointOfInterest>(mainLocations, context.transaction);
  }

  public int getCurrentState(String contextID)
  {
    MainMapContext context;
    context = contexts.get(contextID);
    if (context == null)
      return -1;
    return context.state;
  }
}
