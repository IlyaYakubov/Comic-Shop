package ui.discount;

import domain.sell.CartItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.SearchService;

import java.util.List;

/**
 * Окно списка комиксов
 */
public class ComicListUI extends Application {

    private final DiscountUI discountUI;
    private TableView<CartItem> table;

    public ComicListUI(DiscountUI discountUI) {
        this.discountUI = discountUI;
    }

    /**
     * Отображает окно списка комиксов
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Список комиксов");

        table = new TableView<>();
        table.setPrefHeight(600.0);
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        table.getColumns().add(nameColumn);

        Button buttonOK = new Button("Перенести в акцию");
        buttonOK.setFont(new Font(15));
        buttonOK.setPrefWidth(200);

        VBox vBox = new VBox();
        vBox.getChildren().add(table);
        vBox.getChildren().add(buttonOK);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        SearchService searchService = SearchService.INSTANCE;
        List<CartItem> cartItems = discountUI.getTable().getItems();
        table.setItems(FXCollections.observableArrayList(searchService.getAllCartItems(cartItems)));

        final CartItem[] SELECTED_CART_ITEM = new CartItem[1];
        TableView.TableViewSelectionModel<CartItem> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observableValue, cartItem, newValue) -> {
            if (newValue != null) {
                SELECTED_CART_ITEM[0] = newValue;
            }
        });

        buttonOK.setOnMouseClicked(mouseEvent -> {
            if (SELECTED_CART_ITEM[0] != null) {
                discountUI.setContent(SELECTED_CART_ITEM[0]);
                stage.close();
            }
        });

        Scene scene = new Scene(vBox, 400, 750);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
