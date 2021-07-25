package controllers;

import domain.discounts.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ComicService;

import java.io.IOException;

public class DiscountsController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    @FXML
    private TableView<Discount> tableComics;

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
    void onClickSearch() {
        openWindow("/ui/main.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/report.fxml");
    }

    @FXML
    void onClickOk() {
        openWindow("/ui/discount.fxml");
    }

    @FXML
    void initialize() {
        TableColumn<Discount, String> nameColumn = new TableColumn<>("Наименование акции");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);

        ObservableList<Discount> comicsList = FXCollections.observableArrayList(COMIC_SERVICE.getDiscounts());
        tableComics.setItems(comicsList);
    }

    private void openWindow(String path) {
        tableComics.getScene().getWindow().hide();
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
