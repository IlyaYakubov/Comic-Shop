package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.ComicService;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldNumberOfPages;

    @FXML
    private TextField textFieldYear;

    @FXML
    private TextField textFieldAuthor;

    @FXML
    private TextField textFieldPublishing;

    @FXML
    private TextField textFieldGenre;

    @FXML
    private TextField textFieldCostPrice;

    @FXML
    private TextField textFieldSellPrice;

    @FXML
    private CheckBox checkBoxIsContinue;

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
        openWindow("/ui/sale.fxml");
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
    void onClickReports() {
        openWindow("/ui/report.fxml");
    }

    @FXML
    void onClickOk() {
        if (textFieldName.getText().trim().isEmpty() || textFieldAuthor.getText().trim().isEmpty()
                || textFieldPublishing.getText().trim().isEmpty() || textFieldNumberOfPages.getText().trim().isEmpty()
                || textFieldGenre.getText().trim().isEmpty() || textFieldYear.getText().trim().isEmpty()
                || textFieldCostPrice.getText().trim().isEmpty() || textFieldSellPrice.getText().trim().isEmpty()) {
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

        try {
            Double.parseDouble(textFieldCostPrice.getText().trim());
            Double.parseDouble(textFieldSellPrice.getText().trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();

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
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            MessageController messageController = loader.getController();
            messageController.setMessage("Неверный формат суммы");

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.show();
            return;
        }

        String[] elementsOfComic = elementsOfComic(
                textFieldName.getText().trim(),
                textFieldAuthor.getText().trim(),
                textFieldPublishing.getText().trim(),
                textFieldNumberOfPages.getText().trim(),
                textFieldGenre.getText().trim(),
                textFieldYear.getText().trim(),
                textFieldCostPrice.getText().trim(),
                textFieldSellPrice.getText().trim(),
                checkBoxIsContinue.isSelected(),
                LocalDateTime.now());

        COMIC_SERVICE.addComic(elementsOfComic);

        openWindow("/ui/main.fxml");
    }

    private String[] elementsOfComic(String textFieldNameComic,
                                     String textFieldAuthorComic,
                                     String textFieldPublishingComic,
                                     String textFieldNumberOfPagesComic,
                                     String textFieldGenreComic,
                                     String textFieldYearOfPublishingComic,
                                     String textFieldCoastPriceComic,
                                     String textFieldSellingPriceComic,
                                     boolean checkBoxIsContinuation,
                                     LocalDateTime dateTime) {
        return new String[]{
                textFieldNameComic,
                textFieldAuthorComic,
                textFieldPublishingComic,
                textFieldNumberOfPagesComic,
                textFieldGenreComic,
                textFieldYearOfPublishingComic,
                textFieldCoastPriceComic,
                textFieldSellingPriceComic,
                checkBoxIsContinuation ? "true" : "false",
                String.valueOf(dateTime)};
    }

    private void openWindow(String path) {
        textFieldName.getScene().getWindow().hide();
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
