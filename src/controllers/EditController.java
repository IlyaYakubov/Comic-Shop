package controllers;

import domain.Comic;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import presenters.EditPresenter;

import java.io.IOException;

public class EditController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

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
    void onClickAdd() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/add.fxml"));
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

    @FXML
    void onClickEdit() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/find_comic.fxml"));
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

    @FXML
    void onClickDelete() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/delete.fxml"));
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

    @FXML
    void onClickSell() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/sell.fxml"));
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

    @FXML
    void onClickWriteOff() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/write_off.fxml"));
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

    @FXML
    void onClickReserve() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/reservation.fxml"));
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

    @FXML
    void onClickDiscounts() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/discounts.fxml"));
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

    @FXML
    void onClickSearch() {
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
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void onClickReports() {
        editTextName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/resources/report.fxml"));
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

    @FXML
    void onClickOk() {
        if (editTextName.getText().trim().isEmpty() || editTextAuthor.getText().trim().isEmpty()
                || editTextPublishing.getText().trim().isEmpty() || editTextNumberOfPages.getText().trim().isEmpty()
                || editTextGenre.getText().trim().isEmpty() || editTextYear.getText().trim().isEmpty()
                || editTextCostPrice.getText().trim().isEmpty() || editTextSellPrice.getText().trim().isEmpty()) {
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

        String[] elementsOfComic = elementsOfComic(
                editTextName.getText(),
                editTextAuthor.getText(),
                editTextPublishing.getText(),
                editTextNumberOfPages.getText(),
                editTextGenre.getText(),
                editTextYear.getText(),
                editTextCostPrice.getText(),
                editTextSellPrice.getText(),
                checkBoxIsContinue.isSelected());

        EditPresenter editPresenter = new EditPresenter();
        editPresenter.onClickEdit(elementsOfComic);

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
        stage.setMaximized(true);
        stage.show();
    }

    public void setComic(Comic comic) {
        setContent(comic);
    }

    /**
     * Установка контента в элементы окна
     *
     * @param comic комикс
     */
    public void setContent(Comic comic) {
        editTextName.setText(comic.getName());
        editTextNumberOfPages.setText(String.valueOf(comic.getNumberOfPages()));
        editTextYear.setText(String.valueOf(comic.getYearOfPublishing()));
        editTextAuthor.setText(comic.getAuthor().getName());
        editTextPublishing.setText(comic.getPublishing().getName());
        editTextGenre.setText(comic.getGenre().getName());
        editTextCostPrice.setText(String.valueOf(comic.getComicPrice().getCostPrice()));
        editTextSellPrice.setText(String.valueOf(comic.getComicPrice().getSellingPrice()));
        checkBoxIsContinue.setSelected(comic.isContinuation());
    }

    private String[] elementsOfComic(String textFieldNameComic,
                                     String textFieldAuthorComic,
                                     String textFieldPublishingComic,
                                     String textFieldNumberOfPagesComic,
                                     String textFieldGenreComic,
                                     String textFieldYearOfPublishingComic,
                                     String textFieldCoastPriceComic,
                                     String textFieldSellingPriceComic,
                                     boolean checkBoxIsContinuation) {
        return new String[]{
                textFieldNameComic,
                textFieldAuthorComic,
                textFieldPublishingComic,
                textFieldNumberOfPagesComic,
                textFieldGenreComic,
                textFieldYearOfPublishingComic,
                textFieldCoastPriceComic,
                textFieldSellingPriceComic,
                checkBoxIsContinuation ? "true" : "false"};
    }
}
