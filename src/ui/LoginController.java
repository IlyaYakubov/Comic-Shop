package ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.UserService;
import ui.old.MainUI;
import ui.old.RegistrationUI;
import ui.old.utils.MessageUI;

import java.io.IOException;

/**
 * Окно входа
 */
public class LoginController {

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    @FXML
    void onClickButtonOK() {
        String login = textFieldLogin.getText();
        if (login.equals("")) {
            new MessageUI("Заполните логин").start(new Stage());
            return;
        }
        UserService userService = new UserService(login, textFieldPassword.getText());
        if (!userService.userInTheSystem()) {
            new MessageUI("Пользователь не найден").start(new Stage());
            return;
        }
        textFieldLogin.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources.main.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void onClickButtonRegistration(ActionEvent event) throws Exception {
        RegistrationUI registrationUI = new RegistrationUI();
        registrationUI.start(new Stage());
        ObservableList<Window> windows = Stage.getWindows();
        windows.get(0).hide();
    }
}
