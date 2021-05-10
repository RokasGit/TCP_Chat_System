package mediator;

import java.io.IOException;

public interface ServerModel {
    void sendMessage(String source) throws IOException;
    void receive(String source) throws Exception;
}
