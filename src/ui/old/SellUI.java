package ui.old;

import domain.sell.CartItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.SellPresenter;
import ui.old.reservation.CustomerUI;

import java.util.List;

/**
 * Окно продажи
 */
public class SellUI extends Application {

    private TableView<CartItem> table;
    private Label labelAmount;

    public TableView<CartItem> getTable() {
        return table;
    }

    public String getLabelAmount() {
        return labelAmount.getText();
    }

    /**
     * Отображает окно продажи
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Продажа");

        Label labelComicName = new Label("Наименование");
        labelComicName.setFont(new Font(15));
        TextField textFieldComicName = new TextField();
        textFieldComicName.setFont(new Font(15));
        Button buttonAdd = new Button("Добавить");
        buttonAdd.setFont(new Font(15));
        buttonAdd.setPrefWidth(200);
        Button buttonReserve = new Button("Резерв");
        buttonReserve.setFont(new Font(15));
        buttonReserve.setPrefWidth(200);

        HBox hBox = new HBox();
        hBox.setSpacing(20.0);
        hBox.getChildren().addAll(labelComicName, textFieldComicName, buttonAdd, buttonReserve);

        table = new TableView<>();
        table.setPrefHeight(1000.0);

        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        table.getColumns().add(nameColumn);

        TableColumn<CartItem, Double> priceColumn = new TableColumn<>("Стоимость");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(150.0);
        table.getColumns().add(priceColumn);

        Button buttonSell = new Button("Продать");
        buttonSell.setFont(new Font(15));
        buttonSell.setPrefWidth(200);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(table);

        Label labelTextAmount = new Label("Итого");
        labelTextAmount.setFont(new Font(20));
        labelAmount = new Label("0.0");
        labelAmount.setFont(new Font(20));
        HBox hBoxAmount = new HBox();
        hBoxAmount.getChildren().add(buttonSell);
        hBoxAmount.getChildren().add(labelTextAmount);
        hBoxAmount.getChildren().add(labelAmount);
        hBoxAmount.setSpacing(20.0);

        vBox.getChildren().add(hBoxAmount);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        SellPresenter sellPresenter = new SellPresenter(this);

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            if (checkComicName(textFieldComicName)) {
                return;
            }
            sellPresenter.onClickAdd(textFieldComicName.getText());
            textFieldComicName.setText("");
        });

        buttonReserve.setOnMouseClicked(mouseEvent -> {
            CustomerUI customerUI = new CustomerUI(this);
            customerUI.start(new Stage());
        });

        buttonSell.setOnMouseClicked(mouseEvent -> {
            if (table.getItems().isEmpty()) {
                return;
            }
            sellPresenter.onClickSale();
            table.getItems().clear();
            labelAmount.setText("0.0");
        });

        Scene scene = new Scene(vBox, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Установка контента в элементы окна
     *
     * @param cartItems список комиксов
     * @param amount    сумма
     */
    public void setContent(List<CartItem> cartItems, double amount) {
        table.setItems(FXCollections.observableArrayList(cartItems));
        labelAmount.setText(String.valueOf(amount));
    }

    private boolean checkComicName(TextField textFieldComicName) {
        return textFieldComicName.getText().isEmpty();
    }
}