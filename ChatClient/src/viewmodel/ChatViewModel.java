package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener {
    private Model model;
    private ObservableList<String> chatField;
    private StringProperty message;
    private StringProperty announcement;

    public ChatViewModel(Model model)
    {
        this.model = model;
        this.model.addListener(this);
        chatField = FXCollections.observableArrayList();
        message = new SimpleStringProperty();
        announcement = new SimpleStringProperty();
    }

    public ObservableList<String> getChatField()
    {
        return chatField;
    }

    public StringProperty getMessage(){ return  message;}
    public StringProperty getAnnouncement(){
        return announcement;
    }
    public void sendMessage()
    {
        try
        {
           if(!message.get().equals("") && message.get()!=null && !message.get().toLowerCase().equals("<login")) {
               if(message.get().toLowerCase().equals("<exit")){
                   model.sendMessage(message.get());
                   message.set("");
                   announcement.set("Successfully logged off!");
               }else{
                model.sendMessage(message.get());
                message.set("");
                announcement.set("");
            }}else{
            message.set("");
           throw new IllegalArgumentException("The text you entered can not be sent to the server!");}
        }
        catch (Exception e)
        {
            announcement.set(e.getMessage());
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        Platform.runLater(()->{
            if(evt.getPropertyName().equals("add")){
                chatField.add(0, evt.getNewValue() + "");
            }
        });
    }
}
