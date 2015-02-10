package com.inbiqeba.galk.screen;

import com.inbiqeba.galk.html.HtmlComponent;
import com.inbiqeba.galk.map.Map;

public class MapScreen implements HtmlComponent
{
  private String title;
  private Map map;

  public MapScreen(String title, Map map)
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
