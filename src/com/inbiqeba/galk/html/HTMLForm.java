package com.inbiqeba.galk.html;

import java.util.HashMap;

public class HTMLForm implements HTMLComponent
{
  public static final int INPUT_TYPE_TEXT = 0;
  public static final int INPUT_TYPE_PASSWORD = 1;
  public static final int INPUT_TYPE_NUMBER = 2;
  public static final int INPUT_TYPE_RADIO = 3;
  public static final int INPUT_TYPE_CHECKBOX = 4;
  public static final int INPUT_TYPE_HIDDEN = 5;
  public static final int INPUT_TYPE_SUBMIT = 6;
  private static final int MAX_INPUT_TYPES = 7;
  public static final String typesDescription[] = {"text", "password", "number", "radio", "checkbox", "hidden", "submit"};

  private class FormInput implements HTMLComponent {
    public String name, defaultValue;
    public int type;

    private FormInput(String name, int type, String defaultValue)
    {
      this.name = name;
      this.defaultValue = defaultValue;
      this.type = type;
    }

    @Override
    public String getHTMLHeader()
    {
      return "";
    }

    public String toHTML(String extra)
    {
      String value;
      value = null;
      if (type != INPUT_TYPE_HIDDEN && type != INPUT_TYPE_SUBMIT)
        value = values != null ? values.get(name) : null;
      if (value == null)
        value = defaultValue;
      return String.format("<input type=\"%s\" name=\"%s\" value=\"%s\"%s>", typesDescription[type], name, value, extra != null ? " " + extra : "");
    }

    @Override
    public String toHTML()
    {
     return toHTML(null);
    }
  }

  public static final int METHOD_POST = 0;
  public static final int METHOD_GET = 1;

  private HTMLCollection inputs;
  private String action;
  private int method;
  private HashMap<String, String> values;

  public HTMLForm(String action, int method)
  {
    this.action = action;
    this.method = method;
    this.inputs = new HTMLCollection();
  }

  public void add(String name, int type, String value)
  {
    if (type < 0 || type >= MAX_INPUT_TYPES)
      type = 0;
    inputs.add(new FormInput(name, type, value));
  }

  public void setFormValues(HashMap<String, String> values)
  {
    this.values = values;
  }

  @Override
  public String getHTMLHeader()
  {
    return "";
  }

  @Override
  public String toHTML()
  {
    return String.format("<form action\"%s\" method=\"%s\">\n%s\n</form>", action, method == METHOD_GET ? "GET" : "POST", inputs.toHTML());
  }
}
