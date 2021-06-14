package controllers;

import domain.Comic;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import presenters.EditPresenter;

import java.io.IOException;

public class FindComicController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    @FXML
    private TextField editTextComicName;

    @FXML
    void onClickAdd() {
        openWindow("/ui/resources/add.fxml");
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
    void onClickOk() {
        if (editTextComicName.getText().trim().isEmpty()) {
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
            messageController.setMessage("Введите название комикса");

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
            return;
        }

        EditPresenter editPresenter = new EditPresenter();
        Comic comic = editPresenter.findComicForEdit(editTextComicName.getText().trim());
        if (comic == null) {
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
            messageController.setMessage("Комикс не найден");

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
            return;
        }

        editTextComicName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/edit.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EditController editController = loader.getController();
        editController.setComic(comic);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void onClickBack() {
        openWindow("/ui/resources/main.fxml");
    }

    private void openWindow(String path) {
        editTextComicName.getScene().getWindow().hide();
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
