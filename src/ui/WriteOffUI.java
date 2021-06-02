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
import presenters.WriteOffPresenter;

/**
 * Окно списания
 */
public class WriteOffUI extends Application {

    private TableView<CartItem> table;

    /**
     * Отображает окно списания
     * @param stage - окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Списание");

        Label labelComicName = new Label("Наименование");
        labelComicName.setFont(new Font(15));
        TextField textFieldComicName = new TextField();
        textFieldComicName.setFont(new Font(15));
        Button buttonAdd = new Button("Добавить");
        buttonAdd.setFont(new Font(15));
        buttonAdd.setPrefWidth(200);

        HBox hBox = new HBox();
        hBox.setSpacing(20.0);
        hBox.getChildren().addAll(labelComicName, textFieldComicName, buttonAdd);

        table = new TableView<>();
        table.setPrefHeight(1000.0);

        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("comic"));
        nameColumn.setPrefWidth(350.0);
        table.getColumns().add(nameColumn);

        Button buttonWriteOff = new Button("Списать");
        buttonWriteOff.setFont(new Font(15));
        buttonWriteOff.setPrefWidth(200);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(table);

        vBox.getChildren().add(buttonWriteOff);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        WriteOffPresenter writeOffPresenter = new WriteOffPresenter(this);

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            if (checkComicName(textFieldComicName)) {
                return;
            }
            writeOffPresenter.onClickAdd(textFieldComicName.getText());
            textFieldComicName.setText("");
        });

        buttonWriteOff.setOnMouseClicked(mouseEvent -> {
            if (table.getItems().isEmpty()) {
                return;
            }
            writeOffPresenter.onClickWriteOff();
            table.getItems().clear();
        });

        Scene scene = new Scene(vBox, 700, 500);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    /**
     * Установка контента в элементы окна
     * @param comics - список комиксов
     */
    public void setContent(ObservableList<CartItem> comics) {
        table.setItems(comics);
    }

    private boolean checkComicName(TextField textFieldComicName) {
        return textFieldComicName.getText().isEmpty();
    }
}
