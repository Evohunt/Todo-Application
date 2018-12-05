package todo.Database;

import todo.model.User;
import java.sql.*;

public class DatabaseHandler extends Configs {

    private Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
                + "?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {

        String insert = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_FIRSTNAME
                + ", " + Const.USERS_LASTNAME
                + ", " + Const.USERS_USERNAME
                + ", " + Const.USERS_PASSWORD
                + ", " + Const.USERS_GENDER + ")" + " VALUES(?, ?, ?, ?, ?)";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getGender());

            preparedStatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet validateUser(User user) {

        ResultSet resultSet = null;

        String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                + Const.USERS_USERNAME + "=?";

        try {

            PreparedStatement preparedStatement;
            preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public ResultSet getUser(User user) {

        ResultSet resultSet = null;

        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {

            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD
                    + "=?";

            try {

                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            // Input Validation

        }

        return resultSet;

    }

    public ResultSet getTasksForUser(User user) {

        ResultSet resultSet = null;

        String query = "SELECT * FROM " + Const.TASKS_TABLE
                + " JOIN " + Const.USERS_TABLE + " ON "
                + Const.TASKS_TABLE + "." + Const.USERS_ID
                + " = " + Const.USERS_TABLE + "." + Const.USERS_ID
                + " AND " + Const.USERS_TABLE + "." + Const.USERS_USERNAME
                + "=?";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());

            resultSet = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public void saveTaskForUser(User user, String taskName, String taskDescription) {

        String query = "INSERT INTO " + Const.TASKS_TABLE + " ("
                + Const.TASKS_TABLE + "." + Const.USERS_ID + ", "
                + Const.TASKS_TABLE + "." + Const.TASK_NAME + ", "
                + Const.TASKS_TABLE + "." + Const.TASKS_DESCRIPTION + ")" + " VALUES "
                + "(" + "(" + "SELECT " + Const.USERS_TABLE + "." + Const.USERS_ID + " FROM "
                + Const.USERS_TABLE + " WHERE " + Const.USERS_TABLE + "." + Const.USERS_USERNAME
                + "=?), ?, ?)";


        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, taskName);
            preparedStatement.setString(3, taskDescription);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void removeTask(String taskId) {

        String query = "DELETE FROM " + Const.TASKS_TABLE + " WHERE "
                + Const.TASKS_TABLE + "." + Const.TASKS_ID + "=?";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, taskId);
            preparedStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public ResultSet getTaskId(User user, String taskName) {
        ResultSet resultSet = null;

        String query = "SELECT " + Const.TASKS_TABLE + "." + Const.TASKS_ID
                + " FROM " + Const.TASKS_TABLE + " JOIN " + Const.USERS_TABLE + " ON "
                + Const.USERS_TABLE + "." + Const.USERS_ID + " = "
                + Const.TASKS_TABLE + "." + Const.USERS_ID + " AND "
                + Const.TASKS_TABLE + "." + Const.TASK_NAME + "=?"
                + " AND " + Const.USERS_TABLE + "." + Const.USERS_USERNAME + "=?";

        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, taskName);
            preparedStatement.setString(2, user.getUserName());
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

}
