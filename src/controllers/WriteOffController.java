package controllers;

import domains.Comic;
import domains.sale.Cart;
import domains.sale.CartItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ComicService;
import services.SearchService;

import java.io.IOException;

public class WriteOffController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;
    private final SearchService SEARCH_SERVICE = SearchService.INSTANCE;
    private final Cart CART = new Cart();

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
        openWindow("/ui/sale.fxml");
    }

    @FXML
    void onClickReserve() {
        openWindow("/ui/reservation.fxml");
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
    void buttonAddInCart() {
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
        COMIC_SERVICE.writeOffComics(CART);
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
