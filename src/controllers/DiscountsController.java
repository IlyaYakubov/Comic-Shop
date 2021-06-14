package controllers;

import domain.discounts.Discount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import presenters.DiscountListPresenter;

import java.io.IOException;

public class DiscountsController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final DiscountListPresenter DISCOUNT_PRESENTER = new DiscountListPresenter();

    @FXML
    private TableView<Discount> tableComics;

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
    void onClickSearch() {
        openWindow("/ui/resources/main.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/resources/report.fxml");
    }

    @FXML
    void onClickOk() {
        openWindow("/ui/resources/discount.fxml");
    }

    @FXML
    void initialize() {
        TableColumn<Discount, String> nameColumn = new TableColumn<>("Наименование акции");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);

        DISCOUNT_PRESENTER.setTable(tableComics);
        DISCOUNT_PRESENTER.updateTableDiscounts();
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
