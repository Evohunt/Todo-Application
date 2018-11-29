package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import todo.Database.DatabaseHandler;

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

        DatabaseHandler databaseHandler = new DatabaseHandler();

        registerRegisterButton.setOnAction(event -> {

            databaseHandler.signUpUser(registerFirstName.getText(),
                    registerLastName.getText(),
                    registerUsername.getText(),
                    registerPassword.getText(),
                    "Male");

        });

    }

}
