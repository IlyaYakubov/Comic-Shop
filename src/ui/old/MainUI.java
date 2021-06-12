package ui.old;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui.old.discount.DiscountListUI;
import ui.old.edit.FindBeforeEditUI;
import ui.old.reservation.ReservationUI;

/**
 * Основное окно
 */
public class MainUI extends Application {

    /**
     * Отображает кнопки меню
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Магазин комиксов");

        Button buttonAddComic = new Button("Добавление");
        buttonAddComic.setPrefWidth(200);
        buttonAddComic.setPrefHeight(70);
        buttonAddComic.setFont(new Font(15));
        Button buttonEditComic = new Button("Редактирование");
        buttonEditComic.setPrefWidth(200);
        buttonEditComic.setPrefHeight(70);
        buttonEditComic.setFont(new Font(15));
        Button buttonDeleteComic = new Button("Удаление");
        buttonDeleteComic.setPrefWidth(200);
        buttonDeleteComic.setPrefHeight(70);
        buttonDeleteComic.setFont(new Font(15));
        Button buttonSellComic = new Button("Продажа");
        buttonSellComic.setPrefWidth(200);
        buttonSellComic.setPrefHeight(70);
        buttonSellComic.setFont(new Font(15));
        Button buttonWriteOffComic = new Button("Списание");
        buttonWriteOffComic.setPrefWidth(200);
        buttonWriteOffComic.setPrefHeight(70);
        buttonWriteOffComic.setFont(new Font(15));
        Button buttonReservationComic = new Button("Резервирование");
        buttonReservationComic.setPrefWidth(200);
        buttonReservationComic.setPrefHeight(70);
        buttonReservationComic.setFont(new Font(15));
        Button buttonPromotions = new Button("Акции");
        buttonPromotions.setPrefWidth(200);
        buttonPromotions.setPrefHeight(70);
        buttonPromotions.setFont(new Font(15));
        Button buttonFind = new Button("Поиск");
        buttonFind.setPrefWidth(200);
        buttonFind.setPrefHeight(70);
        buttonFind.setFont(new Font(15));
        Button buttonReports = new Button("Отчеты");
        buttonReports.setPrefWidth(200);
        buttonReports.setPrefHeight(70);
        buttonReports.setFont(new Font(15));

        TilePane tilePane = new TilePane();
        tilePane.setOrientation(Orientation.VERTICAL);
        TilePane.setMargin(buttonAddComic, new Insets(10.0));
        TilePane.setMargin(buttonEditComic, new Insets(10.0));
        TilePane.setMargin(buttonDeleteComic, new Insets(10.0));
        TilePane.setMargin(buttonSellComic, new Insets(10.0));
        TilePane.setMargin(buttonWriteOffComic, new Insets(10.0));
        TilePane.setMargin(buttonReservationComic, new Insets(10.0));
        TilePane.setMargin(buttonPromotions, new Insets(10.0));
        TilePane.setMargin(buttonReports, new Insets(10.0));

        tilePane.getChildren().add(buttonAddComic);
        tilePane.getChildren().add(buttonEditComic);
        tilePane.getChildren().add(buttonDeleteComic);
        tilePane.getChildren().add(buttonSellComic);
        tilePane.getChildren().add(buttonWriteOffComic);
        tilePane.getChildren().add(buttonReservationComic);
        tilePane.getChildren().add(buttonPromotions);
        tilePane.getChildren().add(buttonFind);
        tilePane.getChildren().add(buttonReports);

        buttonAddComic.setOnMouseClicked(mouseEvent -> {
            AdditionUI additionUI = new AdditionUI();
            additionUI.start(new Stage());
        });

        buttonEditComic.setOnMouseClicked(mouseEvent -> {
            FindBeforeEditUI findBeforeEditUI = new FindBeforeEditUI();
            findBeforeEditUI.start(new Stage());
        });

        buttonDeleteComic.setOnMouseClicked(mouseEvent -> {
            DeleteUI deleteUI = new DeleteUI();
            deleteUI.start(new Stage());
        });

        buttonSellComic.setOnMouseClicked(mouseEvent -> {
            SellUI sellUI = new SellUI();
            sellUI.start(new Stage());
        });

        buttonWriteOffComic.setOnMouseClicked(mouseEvent -> {
            WriteOffUI writeOffUI = new WriteOffUI();
            writeOffUI.start(new Stage());
        });

        buttonReservationComic.setOnMouseClicked(mouseEvent -> {
            ReservationUI reservationUI = new ReservationUI();
            reservationUI.start(new Stage());
        });

        buttonPromotions.setOnMouseClicked(mouseEvent -> {
            DiscountListUI discountUI = new DiscountListUI();
            discountUI.start(new Stage());
        });

        buttonFind.setOnMouseClicked(mouseEvent -> {
            SearchUI searchUI = new SearchUI();
            searchUI.start(new Stage());
        });

        buttonReports.setOnMouseClicked(mouseEvent -> {
            ReportUI reportUI = new ReportUI();
            reportUI.start(new Stage());
        });

        Scene scene = new Scene(tilePane, 230, 820);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
