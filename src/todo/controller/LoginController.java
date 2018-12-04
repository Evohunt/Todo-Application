package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todo.Database.DatabaseHandler;
import todo.animations.Shaker;
import todo.model.User;


public class LoginController {

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

            String loginText = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();

            user.setUserName(loginText);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;
            try {

                while (userRow.next()) {
                    counter++;
                }

                if (counter == 1) {
                    showAddItemScreen();
                } else {
                    Shaker usernameShaker = new Shaker(loginUsername);
                    Shaker passwordShaker = new Shaker(loginPassword);
                    usernameShaker.shake();
                    passwordShaker.shake();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        loginRegisterButton.setOnAction(event -> {

            loginRegisterButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/todo/view/register.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

    private void showAddItemScreen() {

        loginRegisterButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/todo/view/addItem.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

    public User getUser() {
        return user;
    }

}
