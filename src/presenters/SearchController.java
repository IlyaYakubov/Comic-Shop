package presenters;

import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.SearchService;

import java.util.List;

/**
 * Контроллер поиска
 */
public class SearchController {

    private final SearchService searchService = SearchService.INSTANCE;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField editTextName;

    @FXML
    private TableView<CartItem> tableComics;

    @FXML
    void buttonFind(ActionEvent event) {
        tableComics.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));

        if (editTextName.getText().isEmpty()) {
            List<CartItem> allComics = searchService.getAllCartItems();
            tableComics.setItems(FXCollections.observableList(allComics));
        } else {
            switch (choiceBox.getValue()) {
                case "по наименованию" -> {
                    List<CartItem> cartItems = searchService.getAllCartItems(editTextName.getText());
                    tableComics.setItems(FXCollections.observableList(cartItems));
                }
                case "по автору" -> {
                    List<CartItem> cartItems = searchService.getComicByAuthor(editTextName.getText());
                    tableComics.setItems(FXCollections.observableList(cartItems));
                }
                case "по жанру" -> {
                    List<CartItem> cartItems = searchService.getComicByGenre(editTextName.getText());
                    tableComics.setItems(FXCollections.observableList(cartItems));
                }
                default -> refreshTable();
            }
        }
    }

    private void refreshTable() {
        tableComics.getItems().clear();
        tableComics.refresh();
    }
}
