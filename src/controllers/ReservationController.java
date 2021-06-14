package controllers;

import domain.sell.CartItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import presenters.ReservationPresenter;

import java.io.IOException;

public class ReservationController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final ReservationPresenter RESERVATION_PRESENTER = new ReservationPresenter();

    @FXML
    private TextField editTextCustomerName;

    @FXML
    private TextField editTextComicName;

    @FXML
    private TableView<CartItem> tableComics;

    @FXML
    void onClickAdd() {
        openWindow("/ui/resources/add.fxml");
    }

    @FXML
    void onClickEdit() {
        openWindow("/ui/resources/find_comic.fxml");
    }

    @FXML
    void onClickDelete() {
        openWindow("/ui/resources/delete.fxml");
    }

    @FXML
    void onClickSell() {
        openWindow("/ui/resources/sell.fxml");
    }

    @FXML
    void onClickWriteOff() {
        openWindow("/ui/resources/write_off.fxml");
    }

    @FXML
    void onClickDiscounts() {
        openWindow("/ui/resources/discounts.fxml");
    }

    @FXML
    void onClickSearch() {
        openWindow("/ui/resources/main.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/resources/report.fxml");
    }

    @FXML
    void onClickAddInCart() {
        RESERVATION_PRESENTER.onClickAdd(editTextComicName.getText().trim());
    }

    @FXML
    void onClickOk() {
        if (tableComics.getItems().size() == 0) {
            return;
        }
        if (editTextCustomerName.getText().trim().isEmpty()) {
            if (Stage.getWindows().size() > 1) {
                ObservableList<Window> windows = Stage.getWindows();
                windows.get(1).requestFocus();
                windows.get(1).centerOnScreen();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/resources/message.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MessageController messageController = loader.getController();
            messageController.setMessage("Заполните имя клиента");

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
            return;
        }

        RESERVATION_PRESENTER.onClickReservation(editTextCustomerName.getText().trim());
        tableComics.getItems().clear();
        tableComics.refresh();
    }

    @FXML
    void initialize() {
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);

        RESERVATION_PRESENTER.setTable(tableComics);
    }

    private void openWindow(String path) {
        editTextCustomerName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMaximized(true);
        stage.show();
    }
}
