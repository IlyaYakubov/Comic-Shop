package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import presenters.SellPresenter;

import java.io.IOException;

public class FindCustomerController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private SellPresenter sellPresenter;

    @FXML
    private TextField editTextCustomerName;

    @FXML
    void onClickOk() {
        if (editTextCustomerName.getText().trim().equals("")) {
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
            messageController.setMessage("Заполните имя клиента");

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
            return;
        }

        if (sellPresenter != null) {
            sellPresenter.onClickReserve(editTextCustomerName.getText().trim());
            editTextCustomerName.getScene().getWindow().hide();
        }
    }

    public void setPresenter(SellPresenter sell_presenter) {
        this.sellPresenter = sell_presenter;
    }
}
