package ui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import presenters.AdditionPresenter;
import presenters.DeletePresenter;

public class DeleteUI extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Удаление");

        Label labelNameComic = new Label("Наименование");
        TextField textFieldComic = new TextField();
        Button buttonDelete = new Button("Удалить");

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(labelNameComic);
        flowPane.getChildren().add(textFieldComic);
        flowPane.getChildren().add(buttonDelete);
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);

        DeletePresenter deletePresenter = new DeletePresenter(this);

        buttonDelete.setOnMouseClicked(mouseEvent -> {
            deletePresenter.onClickDelete(textFieldComic.getText());
        });

        Scene scene = new Scene(flowPane, 200, 200);
        stage.setScene(scene);
        stage.show();
    }
}
