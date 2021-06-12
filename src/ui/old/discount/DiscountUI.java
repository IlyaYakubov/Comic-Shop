package ui.old.discount;

import domain.sell.CartItem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.DiscountPresenter;
import ui.old.utils.MessageUI;

/**
 * Окно создания акции
 */
public class DiscountUI extends Application {

    private final DiscountPresenter DISCOUNT_PRESENTER = new DiscountPresenter(this);
    private int percent;
    private TextField textFieldDiscountName;
    private DatePicker datePickerBegin;
    private DatePicker datePickerEnd;
    private TableView<CartItem> table;

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
     * @param stage окно
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
                    DISCOUNT_PRESENTER.updateTableDiscounts(percent);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonAdd.setOnMouseClicked(mouseEvent -> {
            ComicListUI comicListUI = new ComicListUI(this);
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
            DISCOUNT_PRESENTER.onClickCreate();
            stage.close();
        });

        Scene scene = new Scene(vBox, 1200, 800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Установка контента в элементы окна
     *
     * @param cartItem список комиксов
     */
    public void setContent(CartItem cartItem) {
        table.getItems().addAll(cartItem);
        DISCOUNT_PRESENTER.updateTableDiscounts(percent);
    }
}
