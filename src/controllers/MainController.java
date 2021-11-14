package controllers;

import domains.sale.CartItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.SearchService;

import java.io.IOException;
import java.util.List;

public class MainController {

    private static final String BY_NAME = "по наименованию";
    private static final String BY_AUTHOR = "по автору";
    private static final String BY_GENRE = "по жанру";

    private final int MIN_WIDTH = 1000;
    private final int MIN_HEIGHT = 800;

    private final SearchService SEARCH_SERVICE = SearchService.INSTANCE;

    @FXML
    private ChoiceBox<String> choiceBoxSearchOptions;

    @FXML
    private TextField textFieldFind;

    @FXML
    private TableView<CartItem> tableResult;

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
    void onClickReserve() {
        openWindow("/ui/reservation.fxml");
    }

    @FXML
    void onClickDiscounts() {
        openWindow("/ui/discounts.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/report.fxml");
    }

    @FXML
    void onClickOk() {
        if (textFieldFind.getText().isEmpty()) {
            List<CartItem> allComics = SEARCH_SERVICE.getAllCartItems();
            tableResult.setItems(FXCollections.observableList(allComics));
        } else {
            switch (choiceBoxSearchOptions.getValue()) {
                case BY_NAME -> tableResult.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getAllCartItems(textFieldFind.getText())));
                case BY_AUTHOR -> tableResult.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getComicByAuthor(textFieldFind.getText())));
                case BY_GENRE -> tableResult.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getComicByGenre(textFieldFind.getText())));
                default -> refreshTable();
            }
        }
    }

    @FXML
    void initialize() {
        tableResult.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void refreshTable() {
        tableResult.getItems().clear();
        tableResult.refresh();
    }

    private void openWindow(String path) {
        textFieldFind.getScene().getWindow().hide();
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
