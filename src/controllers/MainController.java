package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> choiceBoxSearchOptions;

    @FXML
    private TextField textFieldFind;

    @FXML
    private TableView<?> tableResult;

    @FXML
    void onClickAdd(ActionEvent event) {

    }

    @FXML
    void onClickDelete(ActionEvent event) {

    }

    @FXML
    void onClickDiscounts(ActionEvent event) {

    }

    @FXML
    void onClickEdit(ActionEvent event) {

    }

    @FXML
    void onClickOk(ActionEvent event) {

    }

    @FXML
    void onClickReports(ActionEvent event) {

    }

    @FXML
    void onClickReserve(ActionEvent event) {

    }

    @FXML
    void onClickSearch(ActionEvent event) {

    }

    @FXML
    void onClickSell(ActionEvent event) {

    }

    @FXML
    void onClickWriteOff(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert choiceBoxSearchOptions != null : "fx:id=\"choiceBoxSearchOptions\" was not injected: check your FXML file 'main.fxml'.";
        assert textFieldFind != null : "fx:id=\"textFieldFind\" was not injected: check your FXML file 'main.fxml'.";
        assert tableResult != null : "fx:id=\"tableResult\" was not injected: check your FXML file 'main.fxml'.";

    }
}
