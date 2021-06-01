package ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.UserService;

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
        String login = textFieldLogin.getText();
        if (login.equals("")) {
            new MessageUI("Имя не может быть пустым").start(new Stage());
            return;
        }
        UserService userService = new UserService(login, textFieldPassword.getText());
        if (!userService.userInTheSystem()) {
            new MessageUI("Пользователь не найден").start(new Stage());
            return;
        }
        MainUI mainUI = new MainUI();
        mainUI.start(new Stage());
        ObservableList<Window> windows = Stage.getWindows();
        windows.get(0).hide();
    }

    @FXML
    void onClickButtonRegistration(ActionEvent event) throws Exception {
        RegistrationUI registrationUI = new RegistrationUI();
        registrationUI.start(new Stage());
        ObservableList<Window> windows = Stage.getWindows();
        windows.get(0).hide();
    }
}
