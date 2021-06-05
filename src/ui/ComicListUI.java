package ui;

import domain.sell.CartItem;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.SearchService;

/**
 * Окно списка комиксов
 */
public class ComicListUI extends Application {

    private TableView<CartItem> table;

    /**
     * Отображает окно списка комиксов
     *
     * @param stage - окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Список комиксов");

        table = new TableView<>();
        table.setPrefHeight(600.0);
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("comic"));
        nameColumn.setPrefWidth(350.0);
        table.getColumns().add(nameColumn);

        Button buttonOK = new Button("Перенести в акцию");
        buttonOK.setFont(new Font(15));
        buttonOK.setPrefWidth(200);

        VBox vBox = new VBox();
        vBox.getChildren().add(table);
        vBox.getChildren().add(buttonOK);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        SearchService searchService = new SearchService();
        table.setItems(searchService.getAllComics());

        buttonOK.setOnMouseClicked(mouseEvent -> {
            stage.close();
        });

        Scene scene = new Scene(vBox, 400, 750);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
