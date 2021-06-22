package controllers;

import domain.Comic;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
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
import services.ComicService;
import services.SearchService;

import java.io.IOException;

public class ReservationController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;
    private final SearchService SEARCH_SERVICE = SearchService.INSTANCE;
    private final Cart CART = new Cart();

    @FXML
    private TextField editTextCustomerName;

    @FXML
    private TextField editTextComicName;

    @FXML
    private TableView<CartItem> tableComics;

    @FXML
    void onClickAdd() {
        openWindow("/ui/add.fxml");
    }

    @FXML
    void onClickEdit() {
        openWindow("/ui/find_comic.fxml");
    }

    @FXML
    void onClickDelete() {
        openWindow("/ui/delete.fxml");
    }

    @FXML
    void onClickSell() {
        openWindow("/ui/sell.fxml");
    }

    @FXML
    void onClickWriteOff() {
        openWindow("/ui/write_off.fxml");
    }

    @FXML
    void onClickDiscounts() {
        openWindow("/ui/discounts.fxml");
    }

    @FXML
    void onClickSearch() {
        openWindow("/ui/main.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/report.fxml");
    }

    @FXML
    void onClickAddInCart() {
        Comic comic = SEARCH_SERVICE.getComicByName(editTextComicName.getText().trim());
        if (comic == null) {
            return;
        }
        CART.addComic(comic);
        tableComics.setItems(FXCollections.observableArrayList(CART.getCartItems()));
        tableComics.refresh();
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
            loader.setLocation(getClass().getResource("/ui/message.fxml"));
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

        COMIC_SERVICE.reserveComics(CART, editTextCustomerName.getText().trim());
        tableComics.getItems().clear();
        tableComics.refresh();
    }

    @FXML
    void initialize() {
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);
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
