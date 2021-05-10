package mediator;

import com.google.gson.Gson;
import javafx.application.Platform;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private Gson gson;
  private String request;
  private String clientName;
  private boolean running;

  public ChatClientHandler(Socket socket, Model model)
      throws IOException
  {
    this.model = model;
    this.socket = socket;
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    gson = new Gson();
    model.addListener(this);
    clientName = null;
  }

  @Override
  public void run()
  {
    Message welcomeMessage = new Message("0","You can use <help to see available commands to you!");
    out.println(gson.toJson(welcomeMessage));
    running = true;
    while (running)
    {
      try
      {
        request = in.readLine();
        Message message = gson.fromJson(request,Message.class);
        model.addLog("Client> " + request);
        switch (message.getBody().toLowerCase()){
          case "<login":
            String nameJson = in.readLine();
            Message login = gson.fromJson(nameJson,Message.class);
            clientName=login.getBody();
            model.addLog("Client> " + nameJson);
            break;
          case "<online":
            Message numberToJson;
            if(model.getNumberOfUsers()!=1){
             numberToJson = new Message("0",model.getNumberOfUsers() + " Online Users");}
            else{
               numberToJson = new Message("0",model.getNumberOfUsers() + " Online User");
            }
            out.println(gson.toJson(numberToJson));
            model.addLog("Server> " + numberToJson);
            break;
          case "<exit":
            model.addLog("Client>"+request);
            model.countUsers(-1);
            close();
            break;
          case "<help":
            model.addLog("Client>"+request);
            Message helpToJson = new Message("0","You can use these commands:\n" +
                    "<help - shows available commands," +
                    "\n<online - shows the number of users online," +
                    "\n<date - shows date and time," +
                    "\n<exit - logs off from the chat system.");
            out.println(gson.toJson(helpToJson));
            break;
          case "<date":
            model.addLog("Client>"+request);
            Message dateToJson = new Message("0","Date and time:");
            out.println(gson.toJson(dateToJson));
            break;
          default:
            message.setName(clientName);
            model.addMessage(gson.toJson(message));
        }
      }
      catch (Exception e)
      {
        model.addLog("Client error");
        model.countUsers(-1);
        close();
      }
    }
    close();
  }
  
  public void close()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();

    }
    catch (IOException e)
    {
      //
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(()->{
      if(evt.getPropertyName().equals("Message")){
        out.println(evt.getNewValue()+"");
      }
    });
  }
}
