package ui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Класс взаимодействия пользователя с программой
 */
public class MainUI extends Application {

    /**
     * Отображает кнопки меню
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Основное окно");

        Button buttonAddComic = new Button("Добавление");
        Button buttonDeleteComic = new Button("Удаление");
        Button buttonSellComic = new Button("Продажа");

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(buttonAddComic);
        flowPane.getChildren().add(buttonDeleteComic);
        flowPane.getChildren().add(buttonSellComic);
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);

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

        Scene scene = new Scene(flowPane, 600, 800);
        stage.setScene(scene);
        stage.show();
    }
}
