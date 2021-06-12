package presenters;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import launch.Main;
import presenters.RegistrationPresenter;
import ui.utils.MessageUI;

import java.io.IOException;

/**
 * Окно регистрации
 */
public class RegistrationController {

    @FXML
    private TextField textFieldName;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private PasswordField textFieldPasswordConfirmation;

    @FXML
    void onClickOK(ActionEvent event) throws IOException {
        String name = textFieldName.getText();
        String password = textFieldPassword.getText();
        if (name.isEmpty()) {
            new MessageUI("Заполните имя пользователя").start(new Stage());
            return;
        }
        if (!password.equals(textFieldPasswordConfirmation.getText())) {
            new MessageUI("Пароли не совпадают").start(new Stage());
            return;
        }
        RegistrationPresenter registrationPresenter = new RegistrationPresenter(name, password);
        registrationPresenter.saveUser();
        ObservableList<Window> windows = Stage.getWindows();
        windows.get(0).hide();
        Main main = new Main();
        main.start(new Stage());
    }
}
