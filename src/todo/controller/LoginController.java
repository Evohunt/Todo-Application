package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import todo.Database.DatabaseHandler;
import todo.animations.Shaker;
import todo.model.User;


public class LoginController extends WindowChangeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginLoginButton;

    @FXML
    private JFXButton loginRegisterButton;

    private DatabaseHandler databaseHandler;

    private static User user = new User();

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        loginLoginButton.setOnAction(event -> {

            if (loginUsername.getText().equals("")) {
                Shaker usernameShaker = new Shaker(loginUsername);
                usernameShaker.shake();
            } else if (loginPassword.getText().equals("")) {
                Shaker passwordShaker = new Shaker(loginPassword);
                passwordShaker.shake();
            } else {
                String loginText = loginUsername.getText().trim();
                String loginPwd = loginPassword.getText().trim();

                user.setUserName(loginText);
                user.setPassword(loginPwd);

                ResultSet userRow = databaseHandler.getUser(user);
                try {
                    if (userRow.next()) {
                        showWindow(loginUsername, "/todo/view/addItem.fxml");
                    } else {
                        Shaker usernameShaker = new Shaker(loginUsername);
                        Shaker passwordShaker = new Shaker(loginPassword);
                        passwordShaker.shake();
                        usernameShaker.shake();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        loginRegisterButton.setOnAction(event -> {

            showWindow(loginRegisterButton, "/todo/view/register.fxml");

        });

    }

    User getUser() {
        return user;
    }
}
