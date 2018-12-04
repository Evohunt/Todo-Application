package todo.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import todo.Database.DatabaseHandler;
import todo.animations.Fader;
import todo.model.User;

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
    private AnchorPane rootAnchorPane;

    @FXML
    private JFXListView<Label> addItemList;

    DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException {

        databaseHandler = new DatabaseHandler();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/todo/view/login.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginController loginController = loader.getController();

        ResultSet tasks = databaseHandler.getTasksForUser(loginController.getUser());
        while (tasks.next()) {
            addItemList.getItems().add(new Label(tasks.getString("task")));
        }

        addAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            System.out.println("Added item!");

            addAddButton.relocate(0, 20);
            noTaskLabel.relocate(0, 85);
            addAddButton.setOpacity(0);
            noTaskLabel.setOpacity(0);

            try {

                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/todo/view/addItemForm.fxml"));

                Fader paneFader = new Fader(formPane, 0f, 1f);
                paneFader.start();

                rootAnchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }


}
