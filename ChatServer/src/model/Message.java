package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String id;
    private String messageBody;
    private LocalDateTime dateTime;
    private String name;

    public Message(String id, String message) {
        this.dateTime = LocalDateTime.now();
        this.id = id;
        this.messageBody = message;
        name="";
    }

    public String getBody() {
        return messageBody;
    }
    public void setName(String name){
        this.name=name;
    }
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return  name+": "+messageBody +" " +dateTime.format(formatter);
    }
}