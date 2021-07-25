package controllers;

import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.SearchService;

import java.util.List;

public class ComicsController {

    private TableView<CartItem> discountTableComics;
    private CartItem[] SELECTED_CART_ITEM = null;
    private int percent;

    @FXML
    private TableView<CartItem> tableComics;

    @FXML
    void onClickOk() {
        if (SELECTED_CART_ITEM[0] != null) {
            discountTableComics.getItems().addAll(SELECTED_CART_ITEM[0]);
            updateTableDiscounts(percent);
            tableComics.getScene().getWindow().hide();
        }
    }

    private void updateTableDiscounts(int percent) {
        discountTableComics.getItems().forEach(cartItem ->
                cartItem.setPrice(cartItem.getComic().getComicPrice().getSellingPrice()));
        if (percent > 0) {
            discountTableComics.getItems().forEach(cartItem -> cartItem.setPrice(
                    cartItem.getComic().getComicPrice().getSellingPrice() -
                            cartItem.getComic().getComicPrice().getSellingPrice() * percent / 100));
        }
        discountTableComics.refresh();
    }

    @FXML
    void initialize() {
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);

        SELECTED_CART_ITEM = new CartItem[1];
        TableView.TableViewSelectionModel<CartItem> selectionModel = tableComics.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observableValue, cartItem, newValue) -> {
            if (newValue != null) {
                SELECTED_CART_ITEM[0] = newValue;
            }
        });
    }

    public void setDiscountTable(TableView<CartItem> tableComics) {
        discountTableComics = tableComics;
        SearchService searchService = SearchService.INSTANCE;
        List<CartItem> cartItems = discountTableComics.getItems();
        this.tableComics.setItems(FXCollections.observableArrayList(searchService.getAllCartItems(cartItems)));
        this.tableComics.refresh();
    }

    public void setPercent(TextField editTextPercent) {
        try {
            percent = Integer.parseInt(editTextPercent.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            percent = 0;
        }
    }
}
