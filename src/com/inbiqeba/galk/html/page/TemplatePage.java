package com.inbiqeba.galk.html.page;

import com.inbiqeba.galk.html.HTMLComponent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public abstract class TemplatePage implements HTMLPage
{
  private HashMap<String, HTMLComponent> templateVariables;
  protected String templateName;

  protected TemplatePage()
  {
    templateName = "default";
    templateVariables = new HashMap<String, HTMLComponent>();
  }

  protected void addTemplateVariable(String name, HTMLComponent component)
  {
    templateVariables.put(name.toUpperCase(), component);
  }

  @Override
  public String getHTMLHeader()
  {
    String ret;
    ret = "";
    for (Map.Entry<String, HTMLComponent> component: templateVariables.entrySet())
      ret += component.getValue().getHTMLHeader();
    return ret;
  }

  private String readFile()
  {
    File file;
    file = new File(String.format("files/pages/templates/%s.html", templateName));
    if (file.exists() && file.canRead()) {
      try {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath(), "")), StandardCharsets.UTF_8); // the lazy way
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return "";
  }

  @Override
  public String toHTML()
  {
    String template;
    template = readFile();
    template = template.replace("%%HEADER%%", getHTMLHeader());
    for (Map.Entry<String, HTMLComponent> component: templateVariables.entrySet())
      template = template.replace(String.format("%%%%%s%%%%", component.getKey()), component.getValue().toHTML());
    return template;
  }
}
