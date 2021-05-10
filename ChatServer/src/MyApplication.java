import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ChatConnector;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {

    try {
      Model model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);
      ChatConnector g = new ChatConnector(model);
      Thread t1 = new Thread(g,"");
      t1.start();
      view.start(primaryStage);

    } catch (Exception e){

    }

  }
}
