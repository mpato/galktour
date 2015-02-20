package com.inbiqeba.galk.html.page;

import com.inbiqeba.galk.html.HTMLComponent;
import java.util.HashMap;

public interface HTMLPage extends HTMLComponent
{
  public void setContextID(String contextID);
  public String getContextID();
  public void initialize();
  public void setFormValues(HashMap<String, String> values);
}
