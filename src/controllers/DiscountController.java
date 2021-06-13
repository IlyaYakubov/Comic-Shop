package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DiscountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dataPickerBegin;

    @FXML
    private DatePicker dataPickerEnd;

    @FXML
    private TextField editTextName;

    @FXML
    private TextField editTextPercent;

    @FXML
    private TableView<?> tableComics;

    @FXML
    void onClickAdd(ActionEvent event) {

    }

    @FXML
    void onClickAddComic(ActionEvent event) {

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
        assert dataPickerBegin != null : "fx:id=\"dataPickerBegin\" was not injected: check your FXML file 'discount.fxml'.";
        assert dataPickerEnd != null : "fx:id=\"dataPickerEnd\" was not injected: check your FXML file 'discount.fxml'.";
        assert editTextName != null : "fx:id=\"editTextName\" was not injected: check your FXML file 'discount.fxml'.";
        assert editTextPercent != null : "fx:id=\"editTextPercent\" was not injected: check your FXML file 'discount.fxml'.";
        assert tableComics != null : "fx:id=\"tableComics\" was not injected: check your FXML file 'discount.fxml'.";

    }
}
