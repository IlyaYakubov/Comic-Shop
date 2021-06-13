package ui.old.reservation;

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
import presenters.ReservationPresenter;
import ui.old.utils.MessageUI;

import java.util.List;

/**
 * Окно резервирования
 */
public class ReservationUI extends Application {

    private TableView<CartItem> table;

    public TableView<CartItem> getTable() {
        return table;
    }

    /**
     * Отображает окно резервирования
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Резервирование");

        Label labelComicName = new Label("Наименование");
        labelComicName.setFont(new Font(15));
        TextField textFieldComicName = new TextField();
        textFieldComicName.setFont(new Font(15));

        HBox hBox = new HBox();
        hBox.setSpacing(20.0);
        hBox.getChildren().addAll(labelComicName, textFieldComicName);

        Label labelCustomerName = new Label("Имя клиента");
        labelCustomerName.setFont(new Font(15));
        TextField textFieldCustomerName = new TextField();
        textFieldCustomerName.setFont(new Font(15));

        HBox hBoxCustomer = new HBox();
        hBox.setSpacing(20.0);
        hBox.getChildren().addAll(labelCustomerName, textFieldCustomerName);

        Button buttonAdd = new Button("Добавить");
        buttonAdd.setFont(new Font(15));
        buttonAdd.setPrefWidth(200);

        table = new TableView<>();
        table.setPrefHeight(1000.0);

        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        table.getColumns().add(nameColumn);

        Button buttonReservation = new Button("Зарезервировать");
        buttonReservation.setFont(new Font(15));
        buttonReservation.setPrefWidth(200);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(hBoxCustomer);
        vBox.getChildren().add(buttonAdd);
        vBox.getChildren().add(table);
        vBox.getChildren().add(buttonReservation);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        ReservationPresenter reservationPresenter = new ReservationPresenter();
        reservationPresenter.setTable(table);

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            if (checkComicName(textFieldComicName)) {
                return;
            }
            reservationPresenter.onClickAdd(textFieldComicName.getText());
            textFieldComicName.setText("");
        });

        buttonReservation.setOnMouseClicked(mouseEvent -> {
            if (table.getItems().isEmpty() || textFieldCustomerName.getText().isEmpty()) {
                new MessageUI("Заполните клиента или комиксы").start(new Stage());
                return;
            }
            reservationPresenter.onClickReservation(textFieldCustomerName.getText());
            textFieldCustomerName.setText("");
            table.getItems().clear();
            stage.close();
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
     */
    public void setContent(List<CartItem> cartItems) {
        table.setItems(FXCollections.observableList(cartItems));
    }

    private boolean checkComicName(TextField textFieldComicName) {
        return textFieldComicName.getText().isEmpty();
    }
}
