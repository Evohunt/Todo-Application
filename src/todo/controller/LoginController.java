package todo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;


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

    @FXML
    void initialize() {

        loginLoginButton.setOnAction(event -> {

            System.out.println("Login clicked!");

        });

    }

}
