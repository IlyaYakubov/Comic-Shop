package ui.old.reservation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presenters.SellPresenter;
import ui.old.SellUI;
import ui.old.utils.MessageUI;

/**
 * Окно ввода клиента
 */
public class CustomerUI extends Application {

    private final SellUI sellUI;

    public CustomerUI(SellUI sellUI) {
        this.sellUI = sellUI;
    }

    /**
     * Отображает окно клиента
     *
     * @param stage окно
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Клиент");

        Label labelCustomerName = new Label("Имя");
        labelCustomerName.setFont(new Font(15));

        TextField textFieldCustomer = new TextField();
        Button buttonFind = new Button("Найти");
        buttonFind.setFont(new Font(15));
        buttonFind.setPrefWidth(265);

        VBox vBox = new VBox();
        vBox.getChildren().add(labelCustomerName);
        vBox.getChildren().add(textFieldCustomer);
        vBox.getChildren().add(buttonFind);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        SellPresenter sellPresenter = new SellPresenter(sellUI);

        buttonFind.setOnMouseClicked(mouseEvent -> {
            if (textFieldCustomer.getText().isEmpty()) {
                new MessageUI("Введите имя клиента").start(new Stage());
                return;
            }
            sellPresenter.onClickReserve(textFieldCustomer.getText());
            stage.close();
        });

        Scene scene = new Scene(vBox, 300, 170);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
