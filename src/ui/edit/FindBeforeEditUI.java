package ui.edit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.utils.MessageUI;

/**
 * Окно поиска перед редактированием комикса
 */
public class FindBeforeEditUI extends Application {

    /**
     * Отображает окно поиска перед редактированием
     *
     * @param stage - окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Редактирование");

        Label labelNameComic = new Label("Наименование комикса");
        labelNameComic.setFont(new Font(15));

        TextField textFieldComic = new TextField();
        Button buttonEdit = new Button("Редактировать");
        buttonEdit.setFont(new Font(15));
        buttonEdit.setPrefWidth(265);

        VBox vBox = new VBox();
        vBox.getChildren().add(labelNameComic);
        vBox.getChildren().add(textFieldComic);
        vBox.getChildren().add(buttonEdit);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        buttonEdit.setOnMouseClicked(mouseEvent -> {
            if (textFieldComic.getText().isEmpty()) {
                new MessageUI("Введите название комикса").start(new Stage());
                return;
            }
            EditUI editUI = new EditUI(textFieldComic.getText());
            editUI.start(new Stage());
            stage.close();
        });

        Scene scene = new Scene(vBox, 300, 170);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
