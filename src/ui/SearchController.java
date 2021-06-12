package ui;

import java.util.List;

import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.SearchService;

/**
 * Контроллер поиска
 */
public class SearchController {

    private final SearchService SEARCH_SERVICE = SearchService.INSTANCE;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField editTextName;

    @FXML
    private TableView<CartItem> tableComics;

    @FXML
    void initialize() {
        tableComics.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    void buttonFind() {
        if (editTextName.getText().isEmpty()) {
            List<CartItem> allComics = SEARCH_SERVICE.getAllCartItems();
            tableComics.setItems(FXCollections.observableList(allComics));
        } else {
            switch (choiceBox.getValue()) {
                case "по наименованию" -> tableComics.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getAllCartItems(editTextName.getText())));
                case "по автору" -> tableComics.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getComicByAuthor(editTextName.getText())));
                case "по жанру" -> tableComics.setItems(FXCollections.observableList(
                        SEARCH_SERVICE.getComicByGenre(editTextName.getText())));
                default -> refreshTable();
            }
        }
    }

    private void refreshTable() {
        tableComics.getItems().clear();
        tableComics.refresh();
    }
}

