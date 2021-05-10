package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient implements ServerModel {
    public static final String HOST = "localhost";
    public static final int PORT = 6789;
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Model model;
    private Gson gson;
    private Thread t1;


    public ChatClient(String host, int port, Model model) {
        this.port = port;
        this.host = host;
        this.model = model;
        try {
            socket = new Socket(host, port);
            gson = new Gson();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected");
        } catch (Exception e) {


        }
        ChatClientReceiver clientReceiver = new ChatClientReceiver(this, in);
        t1 = new Thread(clientReceiver, "");
        t1.start();

    }

    public ChatClient(Model model) {
        this(HOST, PORT, model);
    }
    @Override
    public void receive(String source) throws Exception {
        Message message = gson.fromJson(source, Message.class);
        if(message.getId().equals("0")){
            model.addMessages(message.serverString());
        }else{
        model.addMessages(message.toString());
    }}

    @Override
    public void sendMessage(String source) throws IOException {
        switch (source.toLowerCase()) {
            case "<login":
                Message login = new Message("0", "<login");
                out.println(gson.toJson(login));
                break;
            case "<online":
                Message number = new Message("0", "<online");
                out.println(gson.toJson(number));
                break;
            case "<exit":
                Message exit = new Message("0", "<exit");
                out.println(gson.toJson(exit));
                break;
            case "<help":
                Message help = new Message("0", "<help");
                out.println(gson.toJson(help));
                break;
            case "<date":
                Message dateToJson = new Message("0","<date");
                out.println(gson.toJson(dateToJson));
                break;
            default:
                Message message = new Message("1", source);
                out.println(gson.toJson(message));
        }
    }

}
