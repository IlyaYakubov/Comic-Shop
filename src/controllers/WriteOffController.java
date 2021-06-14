package controllers;

import domain.sell.CartItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import presenters.WriteOffPresenter;

import java.io.IOException;

public class WriteOffController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final WriteOffPresenter WRITE_OFF_PRESENTER = new WriteOffPresenter();

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
    void onClickReserve() {
        openWindow("/ui/resources/reservation.fxml");
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
    void buttonAddInCart() {
        WRITE_OFF_PRESENTER.onClickAdd(editTextComicName.getText().trim());
    }

    @FXML
    void onClickOk() {
        if (tableComics.getItems().size() == 0) {
            return;
        }
        WRITE_OFF_PRESENTER.onClickWriteOff();
        tableComics.getItems().clear();
        tableComics.refresh();
    }

    @FXML
    void initialize() {
        WRITE_OFF_PRESENTER.setTable(tableComics);
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);
    }

    private void openWindow(String path) {
        editTextComicName.getScene().getWindow().hide();
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
