package controllers;

import domain.sell.CartItem;
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
    void onClickReserve() {
        openWindow("/ui/resources/reservation.fxml");
    }

    @FXML
    void onClickDiscounts() {
        openWindow("/ui/resources/discounts.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/resources/report.fxml");
    }

    @FXML
    void onClickOk() {
        if (textFieldFind.getText().isEmpty()) {
            List<CartItem> allComics = SEARCH_SERVICE.getAllCartItems();
            tableResult.setItems(FXCollections.observableList(allComics));
        } else {
            switch (choiceBoxSearchOptions.getValue()) {
                case "по наименованию" -> tableResult.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getAllCartItems(textFieldFind.getText())));
                case "по автору" -> tableResult.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getComicByAuthor(textFieldFind.getText())));
                case "по жанру" -> tableResult.setItems(FXCollections.observableList(
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
