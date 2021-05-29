package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Основное окно
 */
public class MainUI extends Application {

    /**
     * Отображает кнопки меню
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Магазин комиксов");

        Button buttonAddComic = new Button("Добавление");
        buttonAddComic.setPrefWidth(200);
        buttonAddComic.setPrefHeight(70);
        buttonAddComic.setFont(new Font(15));
        Button buttonDeleteComic = new Button("Удаление");
        buttonDeleteComic.setPrefWidth(200);
        buttonDeleteComic.setPrefHeight(70);
        buttonDeleteComic.setFont(new Font(15));
        Button buttonSellComic = new Button("Продажа");
        buttonSellComic.setPrefWidth(200);
        buttonSellComic.setPrefHeight(70);
        buttonSellComic.setFont(new Font(15));

        TilePane tilePane = new TilePane();
        tilePane.setOrientation(Orientation.VERTICAL);
        tilePane.setMargin(buttonAddComic, new Insets(20.0));
        tilePane.setMargin(buttonDeleteComic, new Insets(20.0));
        tilePane.setMargin(buttonSellComic, new Insets(20.0));

        tilePane.getChildren().add(buttonAddComic);
        tilePane.getChildren().add(buttonDeleteComic);
        tilePane.getChildren().add(buttonSellComic);

        buttonAddComic.setOnMouseClicked(mouseEvent -> {
            AdditionUI additionUI = new AdditionUI();
            additionUI.start(new Stage());
        });

        buttonDeleteComic.setOnMouseClicked(mouseEvent -> {
            DeleteUI deleteUI = new DeleteUI();
            deleteUI.start(new Stage());
        });

        buttonSellComic.setOnMouseClicked(mouseEvent -> {
            SellUI sellUI = new SellUI();
            sellUI.start(new Stage());
        });

        Scene scene = new Scene(tilePane, 250, 350);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
