package com.inbiqeba.galk.html;

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

  public void addInitialization(String str)
  {
    initialization += str;
  }

  public void add(String str)
  {
    inline += str;
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
