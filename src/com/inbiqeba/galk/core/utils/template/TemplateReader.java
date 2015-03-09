package com.inbiqeba.galk.core.utils.template;

import com.inbiqeba.galk.core.utils.BufferedInputStream;
import com.inbiqeba.galk.core.utils.ByteBuffer;
import com.inbiqeba.galk.core.utils.ByteObject;
import com.inbiqeba.galk.core.utils.DiscardByteBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class TemplateReader
{
  private static final int STATE_TEXT = 0;
  private static final int STATE_VAR_START = 2;
  private static final int STATE_VAR = 3;
  private static final int STATE_VAR_END = 4;
  private static final int STATE_DONE = 5;
  private BufferedInputStream stream;
  private ByteBuffer output;
  private int state = STATE_TEXT;

  public TemplateReader(InputStream stream)
  {
    this(stream, new ByteBuffer(2048));
  }

  public TemplateReader(InputStream stream, ByteBuffer output)
  {
    this(new BufferedInputStream(stream, 2048), output);
  }

  public ByteBuffer getOutput()
  {
    return output;
  }

  public TemplateReader(BufferedInputStream stream, ByteBuffer output)
  {
    this.stream = stream;
    this.output = output;
  }

  public<T> void process(VariableMap<T> variableMap)
  {
    int read, start, end;
    ByteBuffer varName;
    ByteBuffer currentBuffer, previousBuffer;
    String strVarName;
    start = -1;
    end = -1;
    varName = new ByteBuffer(128);
    currentBuffer = output;
    while (state != STATE_DONE) {
      if (stream.bufferIsFull()) {
        stream.copyToBuffer(currentBuffer, start, end - start);
        end = -1;
        start = -1;
      }
      try {
        read = stream.read();
      } catch (IOException e) {
        state = STATE_DONE;
        break;
      }
      if (read == -1) {
        stream.copyToBuffer(currentBuffer, start, end - start);
        state = STATE_DONE;
        break;
      }
      switch (state) {
        case STATE_VAR:
        case STATE_TEXT:
          if (read == '%') {
            if (state == STATE_TEXT)
              state = STATE_VAR_START;
            else
              state = STATE_VAR_END;
          } else {
            if (start == -1) {
              start = stream.getPosition() - 1;
              end = start;
            }
            end++;
          }
          break;
        case STATE_VAR_END:
        case STATE_VAR_START:
          if (read == '%') {
            if (state == STATE_VAR_START) {
              state = STATE_VAR;
              previousBuffer = output;
              currentBuffer = varName;
            } else {
              state = STATE_TEXT;
              previousBuffer = varName;
              currentBuffer = output;
              varName.clear();
            }
            stream.copyToBuffer(previousBuffer, start, end - start);
            if (state == STATE_TEXT) {
              strVarName = varName.toString().toUpperCase();
              System.out.println("strVarName = " + strVarName);
              if (strVarName.equals("END") || strVarName.startsWith("END "))
                state = STATE_DONE;
              else
                instantiateVar(strVarName, variableMap);
            }
            start = -1;
            end = -1;
          } else {
            if (state == STATE_VAR_START)
              state = STATE_TEXT;
            else
              state = STATE_VAR;
            end += 2;
          }
      }
    }
  }

  private<T> T getVariableMappedValue(String strVarName, VariableMap<T> variableMap)
  {
    if (variableMap == null)
      return null;
    else
      return variableMap.get(strVarName);
  }

  private<T> void instantiateVar(String strVarName, VariableMap<T> variableMap)
  {
    Object obj;
    boolean isSubTemplate = false;
    TemplateReader subReader = null;
    if (strVarName.startsWith("START ")) {
      isSubTemplate = true;
      strVarName = strVarName.replace("START ", "").trim();
    }
    System.out.println("Search strVarName = " + strVarName);
    obj = getVariableMappedValue(strVarName, variableMap);
    if (!isSubTemplate) {
      if (obj != null && obj instanceof ByteObject)
        ((ByteObject)obj).toByteBuffer(output);
    } else {
      if (obj != null) {
        if (obj instanceof TemplateWriter) {
          System.out.println("Here");
          subReader = new TemplateReader(stream, output);
          ((TemplateWriter) obj).instanciate(subReader);
        } else if (obj instanceof ByteObject) {
          ((ByteObject)obj).toByteBuffer(output);
        }
      }
      if (subReader == null)
        subReader = new TemplateReader(stream, new DiscardByteBuffer());
      if (subReader.state != STATE_DONE)
        subReader.process(null);
    }
  }
}
