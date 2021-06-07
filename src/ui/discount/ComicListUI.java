package ui.discount;

import domain.sell.CartItem;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.DiscountPresenter;

/**
 * Окно списка комиксов
 */
public class ComicListUI extends Application {

    private TableView<CartItem> table;
    private DiscountUI discountUI;
    private DiscountPresenter discountPresenter;

    public ComicListUI(DiscountUI discountUI, DiscountPresenter discountPresenter) {
        this.discountUI = discountUI;
        this.discountPresenter = discountPresenter;
    }

    /**
     * Отображает окно списка комиксов
     *
     * @param stage - окно
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

        ObservableList<CartItem> comics = FXCollections.observableArrayList(
                discountPresenter.getFilteredComics(discountUI.getTable().getItems()));
        table.setItems(comics);

        final String[] SELECTED_COMIC_NAME = new String[1];
        TableView.TableViewSelectionModel<CartItem> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observableValue, cartItem, newValue) -> {
            if (newValue != null) {
                SELECTED_COMIC_NAME[0] = newValue.getComic().getName();
            }
        });

        buttonOK.setOnMouseClicked(mouseEvent -> {
            if (SELECTED_COMIC_NAME[0] != null) {
                CartItem comic = discountPresenter.getComicByName(SELECTED_COMIC_NAME[0]);
                if (comic == null) {
                    return;
                }
                ObservableList<CartItem> comicsList = FXCollections.observableArrayList(comic);
                stage.close();
                discountUI.setContent(comicsList);
            }
        });

        Scene scene = new Scene(vBox, 400, 750);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
