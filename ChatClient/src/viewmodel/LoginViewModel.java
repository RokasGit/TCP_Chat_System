package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.io.IOException;

public class LoginViewModel {
    private StringProperty name;
    private StringProperty errorLabel;
    private Model model;
    public LoginViewModel(Model model){
        this.model=model;
        name = new SimpleStringProperty();
        errorLabel = new SimpleStringProperty();
    }
    public StringProperty getName(){
        return name;
    }
    public StringProperty getErrorLabel(){
        return errorLabel;
    }
    public void loginButtonPressed() {
        try{
            model.sendMessage("<login");
            model.sendMessage(name.getValue());
        } catch (Exception e){
            errorLabel.set(e.getMessage());
        }
    }
}
