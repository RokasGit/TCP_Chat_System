package model;



public interface Model extends UnnamedPropertySubject
{
  void countUsers(int i);
  int getNumberOfUsers();
  void addLog(String log);
  void addMessage(String message);
}
