package com.inbiqeba.galk.html.page;

import com.inbiqeba.galk.web.MapPage;

public class MapPageFactory extends PageFactory<MapPage>
{
  @Override
  protected MapPage getNewPage()
  {
    return new MapPage();
  }
}
