package ui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import presenters.SellPresenter;

/**
 * Окно продажи
 */
public class SellUI extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Продажа");

        Label labelNameComic = new Label("Наименование");
        TextField textFieldComic = new TextField();
        Button buttonSell = new Button("Продать");

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(labelNameComic);
        flowPane.getChildren().add(textFieldComic);
        flowPane.getChildren().add(buttonSell);
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);

        SellPresenter sellPresenter = new SellPresenter(this);

        buttonSell.setOnMouseClicked(mouseEvent -> {
            sellPresenter.onClickSale(textFieldComic.getText());
        });

        Scene scene = new Scene(flowPane, 200, 200);
        stage.setScene(scene);
        stage.show();
    }
}
