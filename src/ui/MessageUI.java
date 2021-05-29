package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Окно сообщения
 */
public class MessageUI extends Application {

    private String message;

    public MessageUI(String message) {
        this.message = message;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Сообщение");

        Label labelMessage = new Label();
        labelMessage.setText(message);
        labelMessage.setFont(new Font(15));
        Button buttonOK = new Button("OK");
        buttonOK.setFont(new Font(15));
        buttonOK.setPrefWidth(225);

        VBox vBox = new VBox();
        vBox.getChildren().add(labelMessage);
        vBox.getChildren().add(buttonOK);
        vBox.setSpacing(20.0);
        vBox.setPadding(new Insets(20));

        buttonOK.setOnMouseClicked(mouseEvent -> {
            stage.close();
        });

        Scene scene = new Scene(vBox, 250, 100);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
