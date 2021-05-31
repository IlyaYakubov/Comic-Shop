package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrationUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/registration.fxml"));
        stage.setTitle("Регистрация пользователя");
        stage.setScene(new Scene(root, 450, 270));
        stage.setResizable(false);
        stage.show();
    }
}
