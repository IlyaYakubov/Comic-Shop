package ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Окно входа
 */
public class LoginController {

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    void onClickButtonOK(ActionEvent event) {
        if (textFieldLogin.getText().equals("") && textFieldPassword.getText().equals("")) {
            MainUI mainUI = new MainUI();
            mainUI.start(new Stage());
            ObservableList<Window> windows = Stage.getWindows();
            windows.get(0).hide();
        }
    }

    @FXML
    void onClickButtonRegistration(ActionEvent event) throws Exception {
        RegistrationUI registrationUI = new RegistrationUI();
        registrationUI.start(new Stage());
    }
}
