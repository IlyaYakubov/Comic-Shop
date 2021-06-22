package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.ReportService;

import java.io.IOException;

public class ReportController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final ReportService REPORT_SERVICE = ReportService.INSTANCE;

    @FXML
    private ChoiceBox<String> choiceBoxType;

    @FXML
    private TableView<String> tableComics;

    @FXML
    private DatePicker dateBegin;

    @FXML
    private DatePicker dateEnd;

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
    void onClickDiscounts() {
        openWindow("/ui/discounts.fxml");
    }

    @FXML
    void onClickSearch() {
        openWindow("/ui/main.fxml");
    }

    @FXML
    void onClickOk() {
        if (dateBegin.getValue() == null || dateEnd.getValue() == null) {
            if (Stage.getWindows().size() > 1) {
                ObservableList<Window> windows = Stage.getWindows();
                windows.get(1).requestFocus();
                windows.get(1).centerOnScreen();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/message.fxml"));
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
        if (dateBegin.getValue().isAfter(dateEnd.getValue())) {
            if (Stage.getWindows().size() > 1) {
                ObservableList<Window> windows = Stage.getWindows();
                windows.get(1).requestFocus();
                windows.get(1).centerOnScreen();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/message.fxml"));
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

        switch (choiceBoxType.getValue()) {
            case "продаваемые" -> tableComics.setItems(FXCollections.observableList(
                    REPORT_SERVICE.getTopSold(dateBegin.getValue().toString(), dateEnd.getValue().toString())));
            case "новинки" -> tableComics.setItems(FXCollections.observableList(
                    REPORT_SERVICE.getTopNew(dateBegin.getValue().toString(), dateEnd.getValue().toString())));
            case "топ авторов" -> tableComics.setItems(FXCollections.observableList(
                    REPORT_SERVICE.getTopAuthor(dateBegin.getValue().toString(), dateEnd.getValue().toString())));
            case "топ жанров" -> tableComics.setItems(FXCollections.observableList(
                    REPORT_SERVICE.getTopGenre(dateBegin.getValue().toString(), dateEnd.getValue().toString())));
        }
        tableComics.refresh();
    }

    @FXML
    void initialize() {
        TableColumn<String, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        nameColumn.setPrefWidth(350.0);
        tableComics.getColumns().add(nameColumn);

        choiceBoxType.setItems(FXCollections.observableArrayList("продаваемые", "новинки", "топ авторов", "топ жанров"));
        choiceBoxType.setValue("продаваемые");
    }

    private void openWindow(String path) {
        choiceBoxType.getScene().getWindow().hide();
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
