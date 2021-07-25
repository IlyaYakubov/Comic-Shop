package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MessageController {

    @FXML
    private Label labelTextMessage;

    @FXML
    void onClickButtonOk() {
        labelTextMessage.getScene().getWindow().hide();
    }

    public void setMessage(String message) {
        labelTextMessage.setText(message);
    }
}
