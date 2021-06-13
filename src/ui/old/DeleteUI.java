package ui.old;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.DeletePresenter;
import ui.old.utils.MessageUI;

/**
 * Окно удаления комикса
 */
public class DeleteUI extends Application {

    /**
     * Отображает окно удаления
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Удаление");

        Label labelNameComic = new Label("Наименование комикса");
        labelNameComic.setFont(new Font(15));

        TextField textFieldComic = new TextField();
        Button buttonDelete = new Button("Удалить");
        buttonDelete.setFont(new Font(15));
        buttonDelete.setPrefWidth(265);

        VBox vBox = new VBox();
        vBox.getChildren().add(labelNameComic);
        vBox.getChildren().add(textFieldComic);
        vBox.getChildren().add(buttonDelete);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        buttonDelete.setOnMouseClicked(mouseEvent -> {
            if (textFieldComic.getText().isEmpty()) {
                new MessageUI("Введите название комикса").start(new Stage());
                return;
            }
            DeletePresenter deletePresenter = DeletePresenter.INSTANCE;
            deletePresenter.onClickEdit(textFieldComic.getText());
            stage.close();
        });

        Scene scene = new Scene(vBox, 300, 170);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
