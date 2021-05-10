package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;

public class LoginViewController {
    @FXML private TextField nameInput;
    @FXML private Label errorLabel;
    private LoginViewModel viewModel;
    private ViewHandler viewHandler;
    private Region root;
    public LoginViewController(){

    }
    public void init(ViewHandler viewHandler, LoginViewModel viewModel, Region root){
        this.viewHandler=viewHandler;
        this.viewModel=viewModel;
        this.root=root;
        nameInput.textProperty().bindBidirectional(viewModel.getName());
        errorLabel.textProperty().bind(viewModel.getErrorLabel());
    }
    public Region getRoot(){
        return root;
    }
    @FXML private void onEnter(){
        loginButtonPressed();
    }
    @FXML private void loginButtonPressed(){
            viewModel.loginButtonPressed();
            viewHandler.openView("chatView");
    }
}
