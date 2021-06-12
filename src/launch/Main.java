package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Начало программы
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Отображает главное окно
     *
     * @param stage - окно
     * @throws IOException - исключение
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/resources/login.fxml")));
        stage.setTitle("Вход");
        stage.setScene(new Scene(root, 240, 145));
        stage.setResizable(false);
        stage.show();
    }
}
