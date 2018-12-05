package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import todo.Database.DatabaseHandler;
import todo.animations.Shaker;
import todo.model.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController extends WindowChangeController {

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
    private ImageView registerBack;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        registerBack.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {

            registerRegisterButton.getScene().getWindow().hide();
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

        });

        registerRegisterButton.setOnAction(event -> {

            databaseHandler = new DatabaseHandler();
            ResultSet resultSet = databaseHandler.validateUser(new User(registerUsername.getText()));
            try {
                if (!resultSet.next()) {
                    if (registerFirstName.getText().equals("")) {
                        Shaker shaker = new Shaker(registerFirstName);
                        shaker.shake();
                    } else if (registerLastName.getText().equals("")) {
                        Shaker shaker = new Shaker(registerLastName);
                        shaker.shake();
                    } else if (registerUsername.getText().equals("")) {
                        Shaker shaker = new Shaker(registerUsername);
                        shaker.shake();
                    } else if (registerPassword.getText().equals("")) {
                        Shaker shaker = new Shaker(registerPassword);
                        shaker.shake();
                    } else if (!registerMaleCheckBox.isSelected() && !registerFemaleCheckBox.isSelected()) {
                        Shaker maleShaker = new Shaker(registerMaleCheckBox);
                        Shaker femaleShaker = new Shaker(registerFemaleCheckBox);
                        maleShaker.shake();
                        femaleShaker.shake();
                    } else if (registerMaleCheckBox.isSelected() && registerFemaleCheckBox.isSelected()) {
                        Shaker maleShaker = new Shaker(registerMaleCheckBox);
                        Shaker femaleShaker = new Shaker(registerFemaleCheckBox);
                        maleShaker.shake();
                        femaleShaker.shake();
                    } else {
                        createUser();
                        showWindow(registerFirstName, "/todo/view/login.fxml");
                    }
                } else {
                    Shaker shaker = new Shaker(registerUsername);
                    shaker.shake();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

    }

    private void createUser() {

        databaseHandler = new DatabaseHandler();
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
