package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField editTextName;

    @FXML
    private TextField editTextNumberOfPages;

    @FXML
    private TextField editTextYear;

    @FXML
    private TextField editTextAuthor;

    @FXML
    private TextField editTextPublishing;

    @FXML
    private TextField editTextGenre;

    @FXML
    private TextField editTextCostPrice;

    @FXML
    private TextField editTextSellPrice;

    @FXML
    private CheckBox checkBoxIsContinue;

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
        assert editTextName != null : "fx:id=\"editTextName\" was not injected: check your FXML file 'addition.fxml'.";
        assert editTextNumberOfPages != null : "fx:id=\"editTextNumberOfPages\" was not injected: check your FXML file 'addition.fxml'.";
        assert editTextYear != null : "fx:id=\"editTextYear\" was not injected: check your FXML file 'addition.fxml'.";
        assert editTextAuthor != null : "fx:id=\"editTextAuthor\" was not injected: check your FXML file 'addition.fxml'.";
        assert editTextPublishing != null : "fx:id=\"editTextPublishing\" was not injected: check your FXML file 'addition.fxml'.";
        assert editTextGenre != null : "fx:id=\"editTextGenre\" was not injected: check your FXML file 'addition.fxml'.";
        assert editTextCostPrice != null : "fx:id=\"editTextCostPrice\" was not injected: check your FXML file 'addition.fxml'.";
        assert editTextSellPrice != null : "fx:id=\"editTextSellPrice\" was not injected: check your FXML file 'addition.fxml'.";
        assert checkBoxIsContinue != null : "fx:id=\"checkBoxIsContinue\" was not injected: check your FXML file 'addition.fxml'.";

    }
}
