package com.inbiqeba.galk.html;

import com.inbiqeba.galk.sql.SQLObject;
import com.inbiqeba.galk.sql.SQLSetConverter;

public class SQLJavaScriptConverter<T extends SQLObject & JavaScriptComponent> implements SQLSetConverter<T>
{
  private JavaScriptSnippet scriptSnippet;

  public SQLJavaScriptConverter()
  {
    this.scriptSnippet = new JavaScriptSnippet();
  }

  public JavaScriptSnippet getScriptSnippet()
  {
    return scriptSnippet;
  }

  @Override
  public void convertSetElement(String tag, T element)
  {
   scriptSnippet.add(element.toJavaScript());
  }
}
