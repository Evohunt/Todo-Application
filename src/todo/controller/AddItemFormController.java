package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todo.Database.DatabaseHandler;

public class AddItemFormController {

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
    void initialize() {

        databaseHandler = new DatabaseHandler();

        saveTaskButton.setOnAction(event -> {

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

            taskField.getScene().getWindow().hide();
            FXMLLoader secondLoader = new FXMLLoader();
            secondLoader.setLocation(getClass().getResource("/todo/view/addItem.fxml"));

            try {
                secondLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = secondLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });

    }
}
