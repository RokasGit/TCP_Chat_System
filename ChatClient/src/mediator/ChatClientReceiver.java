package mediator;

import java.io.BufferedReader;

public class ChatClientReceiver implements Runnable{
    private BufferedReader in;
    private ChatClient client;

    public ChatClientReceiver(ChatClient chatClient, BufferedReader in){
        this.in = in;
        this.client = chatClient;
    }

    @Override
    public void run() {
        while (true){
            try {
                String serverReply = in.readLine();
                client.receive(serverReply);
            } catch (Exception e){

            }
        }
    }
}