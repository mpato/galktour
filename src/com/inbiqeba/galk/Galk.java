package com.inbiqeba.galk;

import com.inbiqeba.galk.core.ApplicationContext;
import com.inbiqeba.galk.core.GalkCore;
import com.inbiqeba.galk.html.HTMLQuery;
import com.inbiqeba.galk.html.Mime;
import com.inbiqeba.galk.web.page.MapPageFactory;
import com.inbiqeba.galk.sql.SQLDatabase;
import com.inbiqeba.galk.sql.SQLiteDatabase;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Galk
{
  static GalkCore core;
  static PageDispatcher dispatcher;
  public static void main(String[] args) throws Exception
  {
    Thread t;
    SQLDatabase database;
    database = new SQLiteDatabase("files/galktour");
    ApplicationContext.setDebug(true);
    ApplicationContext.setDataSource(database);
    ApplicationContext.seal();
    database.initialize();
    dispatcher = new PageDispatcher();
    dispatcher.registerPage("/", new MapPageFactory());
    core = new GalkCore();
    core.initialize();
    t = new RequestListenerThread(8085);
    t.setDaemon(false);
    t.start();
    //if (args.length == 2 && args[0].equals("import"))
    //importFrom(args[1]);
  }

  static void importFrom(String fileName)
  {
    System.out.println("Importing from " + fileName);
    String json;
    FileInputStream inputStream;

    try {
      inputStream = new FileInputStream(fileName);
      try {
        json = IOUtils.toString(inputStream);
      } catch (IOException ex) {
        System.out.println("Error reading import file " + fileName);
        return;
      } finally {
        inputStream.close();
        System.out.println("File closed");
      }
      MarkTree markTree;
      markTree = new MarkTree();
      System.out.println("Import: "+ markTree.fromJSON((JSONObject)JSONValue.parse(json)));
      System.out.println("Import: "+ markTree.toJSON().toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println("Error opening import file " + fileName);
    } catch (Exception e) {
      System.out.println("Unknown error while importing file " + fileName);
    }
  }

  static class HttpJSONRequestHandler implements HttpRequestHandler
  {
    public HttpJSONRequestHandler()
    {
      super();
    }

    private void openFile(final HttpResponse response, String target)
    {
      FileEntity fileContent;
      File file;
      file = new File(target.substring(1));
      try {
        if (!file.getCanonicalPath().startsWith(new java.io.File(".").getCanonicalPath() + "/resources")) {
          response.setStatusCode(HttpStatus.SC_FORBIDDEN);
          return;
        }
      } catch (IOException e) {
        e.printStackTrace();
        response.setStatusCode(HttpStatus.SC_FORBIDDEN);
        return;
      }
      if (file.exists() && file.canRead()) {
        fileContent = new FileEntity(file);
        fileContent.setContentType(Mime.getType(target));
        response.setEntity(fileContent);
      } else {
        response.setStatusCode(HttpStatus.SC_NOT_FOUND);
      }
    }

    public void handle(final HttpRequest request, final HttpResponse response, final HttpContext context) throws HttpException, IOException
    {
      String target, html;
      String method;
      HTMLQuery query;
      HttpEntity entity;
      byte[] entityContent;
      StringEntity body;

      entity = null;
      entityContent = new byte[0];
      method = request.getRequestLine().getMethod().toUpperCase(Locale.ENGLISH);
      if (!method.equals("POST") && !method.equals("GET")) {
        throw new MethodNotSupportedException(method + " method not supported");
      }
      query = new HTMLQuery();
      query.fromString(request.getRequestLine().getUri());
      target = query.path;
      System.out.println("target = " + target);
      if (target.startsWith("/resources/")) {
        openFile(response, target);
        return;
      }

      System.out.println(request);
      if (request instanceof HttpEntityEnclosingRequest) {
        entity = ((HttpEntityEnclosingRequest) request).getEntity();
        entityContent = EntityUtils.toByteArray(entity);
        query.addFormData(new String(entityContent));
        System.out.println("Incoming entity content (bytes): " + entityContent.length);
      }

      System.out.println("Entity: " + new String(entityContent));
      response.setStatusCode(HttpStatus.SC_OK);
     // map = new Map(new View(new Transform(new PlainCoordinates(37.41, 8.82), "EPSG:4326", "EPSG:3857"), 4), new RelativeLength(100), new RelativeLength(100));
      html = dispatcher.getPageHTML(target, query.values);
      if (html == null) {
        response.setStatusCode(HttpStatus.SC_NOT_FOUND);
        return;
      }
      body = new StringEntity(html);
      body.setContentType("text/html");
      response.setEntity(body);
      System.out.println("Responding ...");
    }
  }

  static class RequestListenerThread extends Thread
  {
    private final ServerSocket serversocket;
    private final HttpParams params;
    private final HttpService httpService;

    public RequestListenerThread(int port) throws IOException
    {
      this.serversocket = new ServerSocket(port);
      this.params = new SyncBasicHttpParams();
      this.params/*.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000)*/
              .setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8 * 1024)
              .setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false)
              .setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true)
              .setParameter(CoreProtocolPNames.ORIGIN_SERVER, "INBIQEBA_GALK/0.1");

      // Set up the HTTP protocol processor
      HttpProcessor httpproc = new ImmutableHttpProcessor(new HttpResponseInterceptor[]{
        new ResponseDate(),
        new ResponseServer(),
        new ResponseContent(),
        new ResponseConnControl()
      });

      // Set up request handlers
      HttpRequestHandlerRegistry reqistry = new HttpRequestHandlerRegistry();
      reqistry.register("*", new HttpJSONRequestHandler());

      // Set up the HTTP service
      this.httpService = new HttpService(
              httpproc,
              new DefaultConnectionReuseStrategy(),
              new DefaultHttpResponseFactory(),
              reqistry,
              this.params);
    }

    @Override
    public void run()
    {
      System.out.println("Listening on port " + this.serversocket.getLocalPort());
      while (!Thread.interrupted()) {
        try {
          // Set up HTTP connection
          Socket socket = this.serversocket.accept();
          DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
          System.out.println("Incoming connection from " + socket.getInetAddress());
          conn.bind(socket, this.params);

          // Start worker thread
          Thread t = new WorkerThread(this.httpService, conn);
          t.setDaemon(true);
          t.start();
        } catch (InterruptedIOException ex) {
          break;
        } catch (IOException e) {
          System.err.println("I/O error initialising connection thread: "
                  + e.getMessage());
          break;
        }
      }
    }
  }

  static class WorkerThread extends Thread
  {
    private final HttpService httpservice;
    private final HttpServerConnection conn;

    public WorkerThread(final HttpService httpservice, final HttpServerConnection conn)
    {
      super();
      this.httpservice = httpservice;
      this.conn = conn;
    }

    @Override
    public void run()
    {
      System.out.println("New connection thread");
      HttpContext context = new BasicHttpContext(null);
      try {
        while (!Thread.interrupted() && this.conn.isOpen()) {
          this.httpservice.handleRequest(this.conn, context);
        }
      } catch (ConnectionClosedException ex) {
        System.err.println("Client closed connection");
      } catch (IOException ex) {
        System.err.println("I/O error: " + ex.getStackTrace());
        ex.printStackTrace();
      } catch (HttpException ex) {
        System.err.println("Unrecoverable HTTP protocol violation: " + ex.getMessage());
      } finally {
        try {
          this.conn.shutdown();
        } catch (IOException ignore) {
        }
      }
    }
  }

}
