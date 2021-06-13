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
     * Отображает окно входа в систему
     *
     * @param stage окно
     * @throws IOException исключение, если не возможно открыть окно
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/resources/login.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
