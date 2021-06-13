package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    void onClickButtonOK(ActionEvent event) {

    }

    @FXML
    void onClickButtonRegistration(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert textFieldLogin != null : "fx:id=\"textFieldLogin\" was not injected: check your FXML file 'login.fxml'.";
        assert textFieldPassword != null : "fx:id=\"textFieldPassword\" was not injected: check your FXML file 'login.fxml'.";

    }
}
