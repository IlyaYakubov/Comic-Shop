package ui.search;

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
        tableComics.getItems().clear();
        tableComics.setItems(tableComics.getItems());

        if (editTextName.getText().isEmpty()) {
            List<CartItem> allComics = searchService.getAllComics();
            tableComics.setItems(FXCollections.observableList(allComics));
        } else {
            switch (choiceBox.getValue()) {
                case "по наименованию" -> tableComics.getItems().add(searchService.getComicByName(editTextName.getText()));
                case "по автору" -> tableComics.getItems().add(searchService.getComicByAuthor(editTextName.getText()));
                case "по жанру" -> tableComics.getItems().add(searchService.getComicByGenre(editTextName.getText()));
            }
        }
    }
}
