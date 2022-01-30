package peaksoft.dao;

import peaksoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connect;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users" +
                " ( id SERIAL PRIMARY KEY ," +
                "name VARCHAR NOT NULL , " +
                "lastname VARCHAR NOT NULL ," +
                "age INT NOT NULL )";
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table successfully created...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Drop Users Table Users...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User add..." + " " + name);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, (int) id);
            preparedStatement.executeUpdate();
            System.out.println("Remove User By Id...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            int a = 1;
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        (byte) resultSet.getInt("age")));
                a++;
            }
            System.out.println("All: " + a + " Users");
            System.out.println(usersList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Remove All Users...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}