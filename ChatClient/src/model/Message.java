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
        this.name="";
    }

    public String getId(){
        return id;
    }
    public String serverString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm:ss");
        return messageBody + " "+dateTime.format(formatter);
    }
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return name+": "+messageBody +" " + dateTime.format(formatter);
    }
}