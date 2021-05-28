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

public class SellUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Продажа");

        TextField textFieldComic = new TextField();
        Button buttonSell = new Button("Продать");

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(textFieldComic);
        flowPane.getChildren().add(buttonSell);
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);

        SellPresenter sellPresenter = new SellPresenter(this);

        buttonSell.setOnMouseClicked(mouseEvent -> {
            sellPresenter.onClickSale(textFieldComic.getText());
        });

        Scene scene = new Scene(flowPane, 100, 100);
        stage.setScene(scene);
        stage.show();
    }
}
