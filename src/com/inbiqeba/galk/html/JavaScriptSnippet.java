package com.inbiqeba.galk.html;

import com.inbiqeba.galk.ApplicationContext;

public class JavaScriptSnippet
{
  private String initialization;
  private String inline;

  public JavaScriptSnippet()
  {
    this("","");
  }

  public JavaScriptSnippet(String inline)
  {
    this("", inline);
  }

  public JavaScriptSnippet(String initialization, String inline)
  {
    this.initialization = initialization;
    this.inline = inline;
  }

  private String formatString(String str)
  {
    if (ApplicationContext.isDebug())
      str = str.replace("\n", "\n\t");
    else
      str = str.replaceAll("[\n\t]", "");
    return str;
  }

  public void addInitialization(String str)
  {
    initialization += str;
  }

  public void add(String str)
  {
    inline += formatString(str);
  }

  public void add(JavaScriptSnippet snippet)
  {
    addInitialization(snippet.initialization);
    add(snippet.inline);
  }

  public String toString()
  {
    return initialization + inline;
  }
}
