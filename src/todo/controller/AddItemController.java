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


public class AddItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addAddButton;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private JFXListView<Label> addItemList;

    private DatabaseHandler databaseHandler;

    @FXML
    private ImageView addItemRemoveButton;

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
            Label lbl = new Label(tasks.getString("task"));
            addItemList.getItems().add(lbl);
        }

        addItemRemoveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            final int selectedItem = addItemList.getSelectionModel().getSelectedIndex();

            FXMLLoader tempLoader = new FXMLLoader();
            tempLoader.setLocation(getClass().getResource("/todo/view/login.fxml"));
            try {
                tempLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LoginController tempLoginController = tempLoader.getController();

            Label lbl = addItemList.getSelectionModel().getSelectedItem();
            String taskName = lbl.getText();
            ResultSet specificTask = databaseHandler.getTaskId(tempLoginController.getUser(), taskName);
            try {

                while (specificTask.next()) {
                    String taskID = specificTask.getString("taskid");
                    databaseHandler.removeTask(taskID);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            addItemList.getItems().remove(selectedItem);

        });


        addAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            addAddButton.relocate(0, 20);
            addAddButton.setOpacity(0);

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
