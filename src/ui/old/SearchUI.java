package ui.old;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Окно поиска
 */
public class SearchUI extends Application {

    /**
     * Отображает окно поиска
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/resources/search.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Поиск");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.setMinWidth(500);
        stage.setMinHeight(500);
        stage.setResizable(false);
        stage.show();
    }
}
