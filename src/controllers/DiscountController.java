package controllers;

import domain.sell.CartItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import presenters.DiscountPresenter;

import java.io.IOException;

public class DiscountController {
    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final DiscountPresenter discountPresenter = new DiscountPresenter();
    private int percent;

    @FXML
    private DatePicker dataPickerBegin;

    @FXML
    private DatePicker dataPickerEnd;

    @FXML
    private TextField editTextName;

    @FXML
    private TextField editTextPercent;

    @FXML
    private TableView<CartItem> tableComics;

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
    void onClickSearch() {
        openWindow("/ui/resources/main.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/resources/report.fxml");
    }

    @FXML
    void onClickAddComic() {
        if (Stage.getWindows().size() > 1) {
            ObservableList<Window> windows = Stage.getWindows();
            windows.get(1).requestFocus();
            windows.get(1).centerOnScreen();
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/comics.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ComicsController comicsController = loader.getController();
        comicsController.setPercent(editTextPercent);
        comicsController.setDiscountTable(tableComics);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }

    @FXML
    void onClickOk() {
        if (editTextName.getText().isEmpty() || editTextPercent.getText().isEmpty()
                || dataPickerBegin.getValue() == null || dataPickerEnd.getValue() == null) {
            if (Stage.getWindows().size() > 1) {
                ObservableList<Window> windows = Stage.getWindows();
                windows.get(1).requestFocus();
                windows.get(1).centerOnScreen();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/resources/message.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MessageController messageController = loader.getController();
            messageController.setMessage("Заполните все поля");

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
            return;
        }
        if (dataPickerBegin.getValue().isAfter(dataPickerEnd.getValue())) {
            if (Stage.getWindows().size() > 1) {
                ObservableList<Window> windows = Stage.getWindows();
                windows.get(1).requestFocus();
                windows.get(1).centerOnScreen();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/resources/message.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            MessageController messageController = loader.getController();
            messageController.setMessage("Начало не может быть больше окончания");

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
            return;
        }

        discountPresenter.setTextFieldDiscountName(editTextName);
        discountPresenter.setDatePickerBegin(dataPickerBegin);
        discountPresenter.setDatePickerEnd(dataPickerEnd);
        discountPresenter.setPercent(percent);
        discountPresenter.setTable(tableComics);
        discountPresenter.onClickCreate();

        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/main.fxml"));
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
        stage.show();
    }

    @FXML
    void initialize() {
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);

        TableColumn<CartItem, Double> priceColumn = new TableColumn<>("Стоимость");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(150.0);
        tableComics.getColumns().add(priceColumn);

        editTextPercent.setOnAction(actionEvent -> {
            String percentString = editTextPercent.getText();
            if (!percentString.isEmpty()) {
                try {
                    percent = Integer.parseInt(percentString);
                    discountPresenter.setTable(tableComics);
                    discountPresenter.setDatePickerBegin(dataPickerBegin);
                    discountPresenter.setPercent(percent);
                    discountPresenter.setDatePickerEnd(dataPickerEnd);
                    discountPresenter.setTextFieldDiscountName(editTextName);
                    discountPresenter.updateTableDiscounts();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openWindow(String path) {
        editTextName.getScene().getWindow().hide();
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
