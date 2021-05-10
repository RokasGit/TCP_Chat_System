package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class ChatViewController {
    @FXML
    private ListView<String> logList;
    private ChatViewModel viewModel;
    private Region root;
    private ViewHandler viewHandler;
    @FXML private TextField inputField;
    @FXML private Label announcement;
    @FXML private Button submitButton;

    public void init(ViewHandler viewHandler, ChatViewModel viewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        logList.setItems(viewModel.getChatField());
        inputField.textProperty().bindBidirectional(viewModel.getMessage());
        announcement.textProperty().bind(viewModel.getAnnouncement());
        viewModel.getAnnouncement().addListener((obs,oldvalue,newvalue)->{
            if(viewModel.getAnnouncement().get().equals("Successfully logged off!")){
                inputField.setEditable(false);
                inputField.setText("Please exit the program to start again.");
                submitButton.setDisable(true);
                inputField.setOnAction((action)->{
                    // do nothing
                });
            }
        });

    }

    public void reset()
    {
        // empty
    }

    public Region getRoot()
    {
        return root;
    }


    @FXML private void onBack()
    {
        viewHandler.openView("login");
    }

    @FXML private void onEnter(){ submitButtonPressed();}
    @FXML private  void submitButtonPressed(){
        viewModel.sendMessage();
    }
}
