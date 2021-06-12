package ui.old;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.ReportPresenter;

/**
 * Окно отчета
 */
public class ReportUI extends Application {

    private TableView<String> table;

    /**
     * Отображает окно отчета
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Отчет");

        ObservableList<String> options = FXCollections.observableArrayList("продаваемые", "новинки", "топ авторов", "топ жанров");
        ChoiceBox<String> checkBoxOptions = new ChoiceBox<>(options);
        checkBoxOptions.setValue("продаваемые");

        Button buttonAdd = new Button("Сформировать");
        buttonAdd.setFont(new Font(15));
        buttonAdd.setPrefWidth(200);

        HBox hBox = new HBox();
        hBox.setSpacing(20.0);
        hBox.getChildren().addAll(checkBoxOptions, buttonAdd);

        table = new TableView<>();
        table.setPrefHeight(1000.0);

        TableColumn<String, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        nameColumn.setPrefWidth(350.0);
        table.getColumns().add(nameColumn);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(table);

        ReportPresenter reportPresenter = new ReportPresenter(this);

        buttonAdd.setOnMouseClicked(mouseEvent -> reportPresenter.onClick(checkBoxOptions.getValue()));

        Scene scene = new Scene(vBox, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Установка контента в элементы окна
     *
     * @param comics список комиксов
     */
    public void setContent(ObservableList<String> comics) {
        table.setItems(comics);
    }
}
