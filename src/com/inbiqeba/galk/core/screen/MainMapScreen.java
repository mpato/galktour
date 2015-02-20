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
  public static final int STATE_DONE = 98;
  public static final int STATE_DISCARDED = 99;

  private int state;
  private int selectedPOI;
  private SQLTransaction transaction;

  private SQLTable<PointOfInterest> mainLocations;

  public MainMapScreen()
  {
    this.mainLocations = new SQLFilteredTable<PointOfInterest>(ApplicationContext.getPointsOfInterest())
                           .filter("type", SQLFilteredTable.OP_EQUAL, PointOfInterest.TYPE_SUB_MAP);
    state = STATE_SHOW_MAP;
    transaction = ApplicationContext.getDataSource().createNewTransaction();
    transaction.start();
  }

  @Override
  public void discardContext()
  {
    state = STATE_DISCARDED;
    transaction.rollback();
  }

  @Override
  public boolean commitContext()
  {
    state = STATE_DONE;
    return transaction.commit();
  }

  public View<PointOfInterest> getPointsOfInterest()
  {
    return mainLocations.getView(transaction);
  }

  public int getCurrentState()
  {
    return state;
  }

  public void setSelectedPOI(int selectedPOI)
  {
    this.selectedPOI = selectedPOI; //TODO check if the POI exists
  }

  public int getSelectedPOI()
  {
    return this.selectedPOI;
  }
}
