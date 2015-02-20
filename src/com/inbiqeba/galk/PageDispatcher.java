package com.inbiqeba.galk;

import com.inbiqeba.galk.html.page.HTMLPage;
import com.inbiqeba.galk.html.page.PageFactory;
import java.util.HashMap;

public class PageDispatcher
{
  private HashMap<String, PageFactory> pages;

  public PageDispatcher()
  {
    this.pages = new HashMap<String, PageFactory>();
  }

  public void registerPage(String path, PageFactory factory)
  {
    pages.put(path, factory);
  }

  public String getPageHTML(String path, HashMap<String, String> values)
  {
    PageFactory factory;
    HTMLPage page;
    factory = pages.get(path);
    if (factory == null)
      return null;
    System.out.println("values = " + values);
    page = factory.getPage(values != null ? values.get("contextID") : null);
    page.setFormValues(values);
    return page.toHTML();
  }
}
