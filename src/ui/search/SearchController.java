package ui.search;

import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.SearchService;

import java.util.List;

/**
 * Контроллер поиска
 */
public class SearchController {

    private SearchService searchService = SearchService.INSTANCE;

    @FXML
    private TextField editTextName;

    @FXML
    private TableView<CartItem> tableComics;

    @FXML
    void buttonFind(ActionEvent event) {
        tableComics.getItems().clear();
        tableComics.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));

        if (editTextName.getText().isEmpty()) {
            List<CartItem> allComics = searchService.getAllComics();
            tableComics.setItems(FXCollections.observableList(allComics));
        } else {
            CartItem cartItem = searchService.getComicByName(editTextName.getText());
            tableComics.getItems().add(cartItem);
        }
    }
}
