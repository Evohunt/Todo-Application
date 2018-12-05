package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import todo.Database.DatabaseHandler;
import todo.animations.Shaker;

public class AddItemFormController extends WindowChangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField taskDescription;

    @FXML
    private JFXButton saveTaskButton;

    private DatabaseHandler databaseHandler;

    @FXML
    private ImageView addItemBack;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        saveTaskButton.setOnAction(event -> {

            if (taskField.getText().equals("")) {
                Shaker shaker = new Shaker(taskField);
                shaker.shake();
            } else if (taskDescription.getText().equals("")) {
                Shaker shaker = new Shaker(taskDescription);
                shaker.shake();
            } else {
                String taskName = taskField.getText();
                String taskDesc = taskDescription.getText();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/todo/view/login.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LoginController loginController = loader.getController();
                databaseHandler.saveTaskForUser(loginController.getUser(), taskName, taskDesc);
                showWindow(taskField, "/todo/view/addItem.fxml");
            }

        });

        addItemBack.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            showWindow(taskField, "/todo/view/addItem.fxml");

        });

    }

}
