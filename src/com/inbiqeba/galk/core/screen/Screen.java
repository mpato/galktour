package com.inbiqeba.galk.core.screen;

public interface Screen
{
  public String registerNewContext();
  public void discardContext(String contextID);
  public boolean commitContext(String contextID);
}
