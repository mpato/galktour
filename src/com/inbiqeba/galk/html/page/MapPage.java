package com.inbiqeba.galk.html.page;

import com.inbiqeba.galk.html.HTMLComponent;
import com.inbiqeba.galk.html.map.Map;

public class MapPage implements HTMLComponent
{
  private String title;
  private Map map;

  public MapPage(String title, Map map)
  {
    this.title = title;
    this.map = map;
  }

  @Override
  public String getHTMLHeader()
  {
    return map.getHTMLHeader() + "<title>" + title + "</title>";
  }

  @Override
  public String toHTML()
  {
    return "<html>\n<header>\n" + getHTMLHeader() + "\n</header>\n<body>" + map.toHTML() + "\n</body>\n</html>";
  }
}
