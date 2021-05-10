package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{

  private PropertyChangeSupport property;
  private int usersConnected;
  public ModelManager()
  {
    property = new PropertyChangeSupport(this);
    usersConnected=0;
  }


  @Override
  public void countUsers(int i) {

    if(usersConnected+i>=0){
      usersConnected += i;
    }
  }

  @Override
  public int getNumberOfUsers() {
    return usersConnected;
  }

  @Override
  public synchronized void addLog(String log)
  {
    String logValue = LogList.getInstance().getLogSize() + ": " + log;
    LogList.getInstance().addLog(logValue);
    property.firePropertyChange("add", null, logValue);
  }

  @Override public void addMessage(String message){
    property.firePropertyChange("Message", null, message);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
