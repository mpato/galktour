package com.inbiqeba.galk.core.data;

import com.inbiqeba.galk.sql.SQLObject;

public class PointOfInterest implements SQLObject
{
  public static int TYPE_EMPTY = 0;
  public static int TYPE_SUB_MAP = 1;

  public int id;
  public double x, y;
  public String description;
  public int type;
  public int parentId;

  public PointOfInterest(int id, double x, double y, String description, int type, int parentId)
  {
    this.id = id;
    this.x = x;
    this.y = y;
    this.description = description;
    this.type = type;
    this.parentId = parentId;
  }

  public PointOfInterest()
  {
  }
}
