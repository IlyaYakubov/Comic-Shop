package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;

public class ReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> choiceBoxType;

    @FXML
    private TableView<?> tableComics;

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
        assert choiceBoxType != null : "fx:id=\"choiceBoxType\" was not injected: check your FXML file 'report.fxml'.";
        assert tableComics != null : "fx:id=\"tableComics\" was not injected: check your FXML file 'report.fxml'.";

    }
}
