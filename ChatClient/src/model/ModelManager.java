package model;

import mediator.ServerModel;
import mediator.ChatClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private ServerModel serverModel;
  private PropertyChangeSupport property;
  public ModelManager()
  {
    this.serverModel = new ChatClient(this);
    property = new PropertyChangeSupport(this);
  }

  @Override
  public void addMessages(String source) throws Exception {
    property.firePropertyChange("add",null,source);
  }
  @Override
  public synchronized void sendMessage(String source)
  {

    try {
       serverModel.sendMessage(source);
    } catch (Exception e){

    }
  }

  @Override
  public void addListener(PropertyChangeListener listener) {
    property.addPropertyChangeListener(listener);
  }

  @Override
  public void removeListener(PropertyChangeListener listener) {
    property.removePropertyChangeListener(listener);
  }
}
