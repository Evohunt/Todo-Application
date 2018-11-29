package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import todo.Database.DatabaseHandler;
import todo.model.User;

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


    }

}
