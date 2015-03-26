package com.inbiqeba.galk.html.page;

import com.inbiqeba.galk.core.utils.template.TemplateReader;
import com.inbiqeba.galk.core.utils.template.VariableMap;
import com.inbiqeba.galk.html.HTMLComponent;
import com.inbiqeba.galk.html.HTMLSimpleString;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

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

  @Override
  public String toHTML()
  {
    TemplateReader reader;
    File file;
    FileInputStream inputStream;
    templateVariables.put("HEADER", new HTMLSimpleString(getHTMLHeader()));
    file = new File(String.format("files/pages/templates/%s.html", templateName));
    if (file.exists() && file.canRead()) {
      try {
        inputStream = new FileInputStream(file);
        reader = new TemplateReader(inputStream);
        reader.process(new VariableMap<HTMLComponent>(templateVariables));
        inputStream.close();
        return reader.getOutput().toString();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return "";
  }
}
