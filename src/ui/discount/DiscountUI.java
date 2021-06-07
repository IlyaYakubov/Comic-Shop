package ui.discount;

import domain.sell.CartItem;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.DiscountPresenter;
import ui.utils.MessageUI;

/**
 * Окно создания акции
 */
public class DiscountUI extends Application {

    private int percent;
    private TextField textFieldDiscountName;
    private DatePicker datePickerBegin;
    private DatePicker datePickerEnd;
    private TableView<CartItem> table;
    private DiscountPresenter discountPresenter = new DiscountPresenter(this);

    public int getPercent() {
        return percent;
    }

    public TextField getTextFieldDiscountName() {
        return textFieldDiscountName;
    }

    public DatePicker getDatePickerBegin() {
        return datePickerBegin;
    }

    public DatePicker getDatePickerEnd() {
        return datePickerEnd;
    }

    public TableView<CartItem> getTable() {
        return table;
    }

    /**
     * Отображает окно создания акции
     *
     * @param stage - окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Создание акции");

        Label labelDiscountName = new Label("Наименование");
        labelDiscountName.setFont(new Font(15));
        textFieldDiscountName = new TextField();
        textFieldDiscountName.setFont(new Font(15));
        textFieldDiscountName.setPrefWidth(500.0);

        Label labelPercent = new Label("Процент");
        labelPercent.setFont(new Font(15));
        TextField textFieldPercent = new TextField();
        textFieldPercent.setFont(new Font(15));
        textFieldPercent.setPrefWidth(50.0);

        HBox hBoxDiscountName = new HBox();
        hBoxDiscountName.setSpacing(20.0);
        hBoxDiscountName.getChildren().addAll(labelDiscountName, textFieldDiscountName, labelPercent, textFieldPercent);

        Label labelBeginDate = new Label("Начало акции");
        labelBeginDate.setFont(new Font(15));
        datePickerBegin = new DatePicker();
        Label labelEndDate = new Label("Окончание акции");
        labelEndDate.setFont(new Font(15));
        datePickerEnd = new DatePicker();
        HBox hBoxDates = new HBox();
        hBoxDates.setSpacing(20.0);
        hBoxDates.getChildren().addAll(labelBeginDate, datePickerBegin, labelEndDate, datePickerEnd);

        Button buttonAdd = new Button("Добавить комикс");
        buttonAdd.setFont(new Font(15));
        buttonAdd.setPrefWidth(300);

        table = new TableView<>();
        table.setPrefHeight(800.0);
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(550.0);
        table.getColumns().add(nameColumn);
        TableColumn<CartItem, String> priceColumn = new TableColumn<>("Цена со скидкой");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(250.0);
        table.getColumns().add(priceColumn);

        Button buttonCreate = new Button("Создать акцию");
        buttonCreate.setFont(new Font(15));
        buttonCreate.setPrefWidth(500);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBoxDiscountName);
        vBox.getChildren().add(hBoxDates);
        vBox.getChildren().add(buttonAdd);
        vBox.getChildren().add(table);
        vBox.getChildren().add(buttonCreate);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        textFieldPercent.setOnAction(actionEvent -> {
            String percentString = textFieldPercent.getText();
            if (!percentString.isEmpty()) {
                try {
                    percent = Integer.parseInt(percentString);
                    discountPresenter.updateTableDiscounts();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            ComicListUI comicListUI = new ComicListUI(this, discountPresenter);
            comicListUI.start(new Stage());
        });

        buttonCreate.setOnMouseClicked(mouseEvent -> {
            if (textFieldDiscountName.getText().isEmpty() || textFieldPercent.getText().isEmpty()
                    || datePickerBegin.getValue() == null || datePickerEnd.getValue() == null) {
                new MessageUI("Заполните все поля").start(new Stage());
                return;
            }
            if (datePickerBegin.getValue().isAfter(datePickerEnd.getValue())) {
                new MessageUI("Дата начала не может быть больше даты окончания").start(new Stage());
                return;
            }
            discountPresenter.onClickCreate();
            stage.close();
        });

        Scene scene = new Scene(vBox, 1200, 800);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Установка контента в элементы окна
     *
     * @param comics - список комиксов
     */
    public void setContent(ObservableList<CartItem> comics) {
        table.getItems().addAll(comics);
        discountPresenter.updateTableDiscounts();
    }
}
