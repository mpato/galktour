package com.inbiqeba.galk.core;

import com.inbiqeba.galk.core.data.PointOfInterest;
import com.inbiqeba.galk.core.data.PointOfInterestSet;
import com.inbiqeba.galk.sql.SQLDatabase;
import com.inbiqeba.galk.sql.SQLTransaction;

import java.util.Locale;

public class GalkCore
{
  public void initialize()
  {
    SQLDatabase database;
    SQLTransaction transaction;
    Locale.setDefault(Locale.US);
    database = ApplicationContext.getDataSource();
    ApplicationContext.pointsOfInterest = new PointOfInterestSet();
    transaction = database.createNewTransaction();
    transaction.start();
    transaction.executeCreate(ApplicationContext.pointsOfInterest.getCreateQuery());
    ApplicationContext.pointsOfInterest.insertNewRecord(transaction, new PointOfInterest(1, 0, 0, "Kingdom Of Galk", PointOfInterest.TYPE_SUB_MAP, 0));
    ApplicationContext.pointsOfInterest.insertNewRecord(transaction, new PointOfInterest(2, 0, 0, "Zero island", PointOfInterest.TYPE_EMPTY, 1));
    ApplicationContext.pointsOfInterest.insertNewRecord(transaction, new PointOfInterest(3, 10, 10, "Some other point island", PointOfInterest.TYPE_EMPTY, 1));
    ApplicationContext.pointsOfInterest.insertNewRecord(transaction, new PointOfInterest(4, -20, 10, "Federal Republic of Northen Galk", PointOfInterest.TYPE_SUB_MAP, 0));
    ApplicationContext.pointsOfInterest.insertNewRecord(transaction, new PointOfInterest(5, -170, 100, "Evil Land of Non-Galk", PointOfInterest.TYPE_SUB_MAP, 0));
    transaction.commit();
  }
}
