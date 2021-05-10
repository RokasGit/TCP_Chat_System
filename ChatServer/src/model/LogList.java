package model;

import java.util.ArrayList;

public class LogList
{
  private static LogList logList;
  private ArrayList<String> list;
  private static Object lock = new Object();
  
  private LogList()
  {
    this.list = new ArrayList<>();
  }
  public static LogList getInstance(){
    if(logList==null){
      synchronized (lock){
        if(logList==null){
          logList = new LogList();
        }
      }
    }
    return logList;
  }
  public void addLog(String txt)
  {
    list.add(txt);
  }

  public int getLogSize()
  {
    return list.size();
  }
  

}
