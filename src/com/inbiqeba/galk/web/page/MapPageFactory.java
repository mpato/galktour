package com.inbiqeba.galk.web.page;

import com.inbiqeba.galk.html.page.PageFactory;

public class MapPageFactory extends PageFactory<MapPage>
{
  @Override
  protected MapPage getNewPage()
  {
    return new MapPage();
  }
}
