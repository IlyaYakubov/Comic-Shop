package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.AdditionPresenter;

/**
 * Окно добавления комикса
 */
public class AdditionUI extends Application {

    private TextField textFieldNameComic = new TextField();
    private TextField textFieldNumberOfPagesComic = new TextField();
    private TextField textFieldYearOfPublishingComic = new TextField();
    private TextField textFieldAuthorComic = new TextField();
    private TextField textFieldPublishingComic = new TextField();
    private TextField textFieldGenreComic = new TextField();
    private TextField textFieldCoastPriceComic = new TextField();
    private TextField textFieldSellingPriceComic = new TextField();
    private CheckBox checkBoxIsContinuation = new CheckBox();

    private Button buttonAdd;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Добавление");

        Label labelNameComic = new Label("Наименование");
        Label labelNumberOfPagesComic = new Label("Количество страниц");
        Label labelYearOfPublishingComic = new Label("Год издания");
        Label labelAuthorComic = new Label("ФИО художника/автора");
        Label labelPublishingComic = new Label("Издательство");
        Label labelGenreComic = new Label("Жанр");
        Label labelCoastPriceComic = new Label("Себестоимость");
        Label labelSellingPriceComic = new Label("Цена продажи");
        Label labelIsContinuation = new Label("Комикс является продолжением серии");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20.0);
        gridPane.setVgap(20.0);
        gridPane.add(labelNameComic, 0, 0 );
        gridPane.add(textFieldNameComic, 1, 0);
        gridPane.add(labelNumberOfPagesComic, 0, 1);
        gridPane.add(textFieldNumberOfPagesComic, 1, 1);
        gridPane.add(labelYearOfPublishingComic, 0, 2);
        gridPane.add(textFieldYearOfPublishingComic, 1, 2);
        gridPane.add(labelAuthorComic, 0, 3);
        gridPane.add(textFieldAuthorComic, 1, 3);
        gridPane.add(labelPublishingComic, 0, 4);
        gridPane.add(textFieldPublishingComic, 1, 4);
        gridPane.add(labelGenreComic, 0, 5);
        gridPane.add(textFieldGenreComic, 1, 5);
        gridPane.add(labelCoastPriceComic, 0, 6);
        gridPane.add(textFieldCoastPriceComic, 1, 6);
        gridPane.add(labelSellingPriceComic, 0, 7);
        gridPane.add(textFieldSellingPriceComic, 1, 7);
        gridPane.add(labelIsContinuation, 0, 8);
        gridPane.add(checkBoxIsContinuation, 1, 8);

        buttonAdd = new Button("Добавить");
        buttonAdd.setFont(new Font(15));
        buttonAdd.setPrefWidth(385);
        buttonAdd.setPrefHeight(100);

        VBox vBox = new VBox();
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(buttonAdd);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        AdditionPresenter additionPresenter = new AdditionPresenter(this);

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            if (textFieldNameComic.getText().isEmpty()) {
                new MessageUI("Не заполнены поля!").start(new Stage());
                return;
            }
            String[] elementsOfComic = elementsOfComic(textFieldNameComic.getText(), textFieldNumberOfPagesComic.getText()
                    , textFieldYearOfPublishingComic.getText(), textFieldAuthorComic.getText()
                    , textFieldPublishingComic.getText(), textFieldGenreComic.getText()
                    , textFieldCoastPriceComic.getText(), textFieldSellingPriceComic.getText()
                    , checkBoxIsContinuation.isSelected());

            additionPresenter.onClickAdd(elementsOfComic);
            stage.close();
        });

        Scene scene = new Scene(vBox, 425, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private String[] elementsOfComic(String textFieldNameComic,
                                     String textFieldNumberOfPagesComic,
                                     String textFieldYearOfPublishingComic,
                                     String textFieldAuthorComic,
                                     String textFieldPublishingComic,
                                     String textFieldGenreComic,
                                     String textFieldCoastPriceComic,
                                     String textFieldSellingPriceComic,
                                     boolean checkBoxIsContinuation) {
        String[] elementsOfComic = {textFieldNameComic, textFieldNumberOfPagesComic, textFieldYearOfPublishingComic
        , textFieldAuthorComic, textFieldPublishingComic, textFieldGenreComic, textFieldCoastPriceComic
        , textFieldSellingPriceComic, checkBoxIsContinuation ? "true" : "false"};
        return elementsOfComic;
    }
}
