package todo.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class AddItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addAddButton;

    @FXML
    private Label noTaskLabel;

    @FXML
    void initialize() {

        addAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addAddButton);
            FadeTransition labelTransiton = new FadeTransition(Duration.millis(2000), noTaskLabel);

            System.out.println("Added item!");
            addAddButton.relocate(0, 20);
            noTaskLabel.relocate(0, 85);

            addAddButton.setOpacity(0);
            noTaskLabel.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            labelTransiton.setFromValue(1f);
            labelTransiton.setToValue(0f);
            labelTransiton.setCycleCount(1);
            labelTransiton.setAutoReverse(false);
            labelTransiton.play();

        });

    }
}
