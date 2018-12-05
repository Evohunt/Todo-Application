package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todo.Database.DatabaseHandler;
import todo.model.User;

import java.io.IOException;

public class RegisterController {

    @FXML
    private JFXTextField registerFirstName;

    @FXML
    private JFXTextField registerLastName;

    @FXML
    private JFXTextField registerUsername;

    @FXML
    private JFXCheckBox registerMaleCheckBox;

    @FXML
    private JFXCheckBox registerFemaleCheckBox;

    @FXML
    private JFXPasswordField registerPassword;

    @FXML
    private JFXButton registerRegisterButton;

    @FXML
    void initialize() {

        registerRegisterButton.setOnAction(event -> {

            createUser();

        });

    }

    private void createUser() {

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = registerFirstName.getText();
        String lastName = registerLastName.getText();
        String userName = registerUsername.getText();
        String password = registerPassword.getText();

        String gender = "";
        if (registerMaleCheckBox.isSelected()) {
            gender = "Male";
        } else if (registerFemaleCheckBox.isSelected()) {
            gender = "Female";
        }

        User user = new User(firstName, lastName, userName, password, gender);

        databaseHandler.signUpUser(user);

        registerMaleCheckBox.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/todo/view/login.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();


    }

}
