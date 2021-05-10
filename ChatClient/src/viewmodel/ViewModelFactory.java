package viewmodel;

import model.Model;

import java.io.IOException;

public class ViewModelFactory
{
  private ChatViewModel chatViewModel;
  private LoginViewModel loginViewModel;
  public ViewModelFactory(Model model)
  {
    chatViewModel= new ChatViewModel(model);
    loginViewModel = new LoginViewModel(model);
  }

  public ChatViewModel getChatViewModel(){
    return chatViewModel;
  }
  public LoginViewModel getLoginViewModel(){
    return loginViewModel;
  }
}
