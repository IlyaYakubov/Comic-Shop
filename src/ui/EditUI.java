package ui;

import domain.Comic;
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
import presenters.EditPresenter;

/**
 * Окно редактирования комикса
 */
public class EditUI extends Application {

    private TextField textFieldNameComic;
    private TextField textFieldNumberOfPagesComic;
    private TextField textFieldYearOfPublishingComic;
    private TextField textFieldAuthorComic;
    private TextField textFieldPublishingComic;
    private TextField textFieldGenreComic;
    private TextField textFieldCoastPriceComic;
    private TextField textFieldSellingPriceComic;
    private CheckBox checkBoxIsContinuation;

    private String comicName;

    public EditUI(String comicName) {
        this.comicName = comicName;
    }

    /**
     * Отображает окно редактирования комикса
     *
     * @param stage - окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Редактирование");

        Label labelNameComic = new Label("Наименование");
        Label labelNumberOfPagesComic = new Label("Количество страниц");
        Label labelYearOfPublishingComic = new Label("Год издания");
        Label labelAuthorComic = new Label("ФИО художника/автора");
        Label labelPublishingComic = new Label("Издательство");
        Label labelGenreComic = new Label("Жанр");
        Label labelCoastPriceComic = new Label("Себестоимость");
        Label labelSellingPriceComic = new Label("Цена продажи");
        Label labelIsContinuation = new Label("Комикс является продолжением серии");

        textFieldNameComic = new TextField();
        textFieldNumberOfPagesComic = new TextField();
        textFieldYearOfPublishingComic = new TextField();
        textFieldAuthorComic = new TextField();
        textFieldPublishingComic = new TextField();
        textFieldGenreComic = new TextField();
        textFieldCoastPriceComic = new TextField();
        textFieldSellingPriceComic = new TextField();
        checkBoxIsContinuation = new CheckBox();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20.0);
        gridPane.setVgap(20.0);
        gridPane.add(labelNameComic, 0, 0);
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

        Button buttonAdd = new Button("Редактировать");
        buttonAdd.setFont(new Font(15));
        buttonAdd.setPrefWidth(385);
        buttonAdd.setPrefHeight(100);

        VBox vBox = new VBox();
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(buttonAdd);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        EditPresenter editPresenter = new EditPresenter(this);
        editPresenter.findComicForEdit(comicName);

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            if (textFieldNameComic.getText().isEmpty() || textFieldAuthorComic.getText().isEmpty()
                    || textFieldPublishingComic.getText().isEmpty() || textFieldNumberOfPagesComic.getText().isEmpty()
                    || textFieldGenreComic.getText().isEmpty() || textFieldYearOfPublishingComic.getText().isEmpty()
                    || textFieldCoastPriceComic.getText().isEmpty() || textFieldSellingPriceComic.getText().isEmpty()) {
                new MessageUI("Заполните все поля").start(new Stage());
                return;
            }
            String[] elementsOfComic = elementsOfComic(
                    textFieldNameComic.getText(),
                    textFieldAuthorComic.getText(),
                    textFieldPublishingComic.getText(),
                    textFieldNumberOfPagesComic.getText(),
                    textFieldGenreComic.getText(),
                    textFieldYearOfPublishingComic.getText(),
                    textFieldCoastPriceComic.getText(),
                    textFieldSellingPriceComic.getText(),
                    checkBoxIsContinuation.isSelected());

            editPresenter.onClickEdit(elementsOfComic);
            stage.close();
        });

        Scene scene = new Scene(vBox, 425, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Установка контента в элементы окна
     *
     * @param comic - комикс
     */
    public void setContent(Comic comic) {
        textFieldNameComic.setText(comic.getName());
        textFieldNumberOfPagesComic.setText(String.valueOf(comic.getNumberOfPages()));
        textFieldYearOfPublishingComic.setText(String.valueOf(comic.getYearOfPublishing()));
        textFieldAuthorComic.setText(comic.getAuthor().getName());
        textFieldPublishingComic.setText(comic.getPublishing().getName());
        textFieldGenreComic.setText(comic.getGenre().getName());
        textFieldCoastPriceComic.setText(String.valueOf(comic.getComicPrice().getCostPrice()));
        textFieldSellingPriceComic.setText(String.valueOf(comic.getComicPrice().getSellingPrice()));
        checkBoxIsContinuation.setSelected(comic.isContinuation());
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
