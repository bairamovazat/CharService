package ru.ivmiit.dao;

import lombok.SneakyThrows;
import ru.ivmiit.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDaoImpl implements UsersDao {

    private static UsersDaoImpl userRepository;
    private static String tableName = "user";

    private Connection connection;

    private UsersDaoImpl() {
        try {
            connection = DriverManager.getConnection(DBCredentialData.getURL(), DBCredentialData.getUsername(), DBCredentialData.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UsersDaoImpl userRepository = new UsersDaoImpl();
        User user = new User();
        user.setName("azat");
        user.setPasswordHash("Password_hash");
        user.setSessionID("asd");
    }

    public static UsersDaoImpl getInstance() {
        if (userRepository == null) {
            userRepository = new UsersDaoImpl();
        }
        return userRepository;
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"" + tableName + "\" WHERE id = ?;");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPasswordHash(resultSet.getString("passwordhash"));
            user.setSessionID(resultSet.getString("sessionid"));
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"" + tableName + "\";");
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPasswordHash(resultSet.getString("passwordhash"));
                user.setSessionID(resultSet.getString("sessionid"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<User> getUserByNameAndPassword(String user, String password) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"" + tableName + "\" WHERE name = ? AND passwordhash = ?;");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User userObj = new User();
                userObj.setId(resultSet.getLong("id"));
                userObj.setName(resultSet.getString("name"));
                userObj.setPasswordHash(resultSet.getString("passwordhash"));
                userObj.setSessionID(resultSet.getString("sessionid"));
                return Optional.of(userObj);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    @SneakyThrows
    public void save(User obj) {
        PreparedStatement preparedStatement;
        if (obj.getId() == null) {
            preparedStatement = connection.prepareStatement("INSERT INTO \"" + tableName + "\" (name, passwordhash, sessionid) VALUES (?,?,?);");
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getPasswordHash());
            preparedStatement.setString(3, obj.getSessionID());
            preparedStatement.execute();
        } else {
            preparedStatement = connection.prepareStatement("INSERT INTO \"" + tableName + "\" (id, name, passwordhash, sessionid) VALUES (?,?,?,?);");
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setString(3, obj.getPasswordHash());
            preparedStatement.setString(4, obj.getSessionID());
            preparedStatement.execute();
        }
    }

    @Override
    @SneakyThrows
    public void update(User obj) {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE \"" + tableName + "\" SET name = ?, passwordhash = ?, sessionid = ? WHERE id = ?;");
        preparedStatement.setString(1, obj.getName());
        preparedStatement.setString(2, obj.getPasswordHash());
        preparedStatement.setString(3, obj.getSessionID());
        preparedStatement.setLong(4, obj.getId());
        preparedStatement.execute();

    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"" + tableName + "\" WHERE id = ?;");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getUserBySessionId(String sessionId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"" + tableName + "\" WHERE sessionid = ?;");
            preparedStatement.setString(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPasswordHash(resultSet.getString("passwordhash"));
            user.setSessionID(resultSet.getString("sessionid"));
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
