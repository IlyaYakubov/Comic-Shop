package ui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import presenters.AdditionPresenter;

public class AdditionUI extends Application {

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

        TextField textFieldNameComic = new TextField();
        TextField textFieldNumberOfPagesComic = new TextField();
        TextField textFieldYearOfPublishingComic = new TextField();
        TextField textFieldAuthorComic = new TextField();
        TextField textFieldPublishingComic = new TextField();
        TextField textFieldGenreComic = new TextField();
        TextField textFieldCoastPriceComic = new TextField();
        TextField textFieldSellingPriceComic = new TextField();
        CheckBox checkBoxIsContinuation = new CheckBox();

        Button buttonAdd = new Button("Добавить");

        FlowPane flowPaneLeft = new FlowPane();
        flowPaneLeft.getChildren().add(labelNameComic);
        flowPaneLeft.getChildren().add(labelNumberOfPagesComic);
        flowPaneLeft.getChildren().add(labelYearOfPublishingComic);
        flowPaneLeft.getChildren().add(labelAuthorComic);
        flowPaneLeft.getChildren().add(labelPublishingComic);
        flowPaneLeft.getChildren().add(labelGenreComic);
        flowPaneLeft.getChildren().add(labelCoastPriceComic);
        flowPaneLeft.getChildren().add(labelSellingPriceComic);
        flowPaneLeft.getChildren().add(labelIsContinuation);
        flowPaneLeft.setVgap(10);
        flowPaneLeft.setHgap(10);
        flowPaneLeft.setOrientation(Orientation.VERTICAL);

        FlowPane flowPaneRight = new FlowPane();
        flowPaneRight.getChildren().add(textFieldNameComic);
        flowPaneRight.getChildren().add(textFieldNumberOfPagesComic);
        flowPaneRight.getChildren().add(textFieldYearOfPublishingComic);
        flowPaneRight.getChildren().add(textFieldAuthorComic);
        flowPaneRight.getChildren().add(textFieldPublishingComic);
        flowPaneRight.getChildren().add(textFieldGenreComic);
        flowPaneRight.getChildren().add(textFieldCoastPriceComic);
        flowPaneRight.getChildren().add(textFieldSellingPriceComic);
        flowPaneRight.getChildren().add(checkBoxIsContinuation);
        flowPaneRight.getChildren().add(buttonAdd);
        flowPaneRight.setVgap(10);
        flowPaneRight.setHgap(10);
        flowPaneRight.setOrientation(Orientation.VERTICAL);

        HBox hBox = new HBox();
        hBox.getChildren().add(flowPaneLeft);
        hBox.getChildren().add(flowPaneRight);

        AdditionPresenter additionPresenter = new AdditionPresenter(this);

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            String[] elementsOfComic = elementsOfComic(textFieldNameComic.getText(), textFieldNumberOfPagesComic.getText()
                    , textFieldYearOfPublishingComic.getText(), textFieldAuthorComic.getText()
                    , textFieldPublishingComic.getText(), textFieldGenreComic.getText()
                    , textFieldCoastPriceComic.getText(), textFieldSellingPriceComic.getText()
                    , checkBoxIsContinuation.isSelected());
            additionPresenter.onClickAdd(elementsOfComic);
        });

        Scene scene = new Scene(hBox, 500, 350);
        stage.setScene(scene);
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
