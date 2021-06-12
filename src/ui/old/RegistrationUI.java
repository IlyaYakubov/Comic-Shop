package ui.old;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class RegistrationUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/resources/registration.fxml")));
        stage.setTitle("Регистрация пользователя");
        stage.setScene(new Scene(root, 355, 230));
        stage.setResizable(false);
        stage.show();
    }
}
