package com.inbiqeba.galk.html.page;

import java.util.HashMap;
import java.util.UUID;

public abstract class PageFactory<T extends HTMLPage>
{
  private HashMap<String, T> pages;

  protected abstract T getNewPage();

  public PageFactory()
  {
    this.pages = new HashMap<String, T>();
  }

  public HTMLPage getPage(String contextID)
  {
    String tag;
    T page;
    System.out.println("contextID = " + contextID);
    if (contextID != null) {
      System.out.println("contextID = " + contextID);
      page = pages.get(contextID);
      System.out.println("page = " + page);
      if (page != null)
        return page;
    }
    page = getNewPage();
    tag = UUID.randomUUID().toString();
    page.setContextID(tag);
    page.initialize();
    pages.put(tag, page);
    return page;
  }
}
