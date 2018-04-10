package ru.ivmiit.repositories;

import ru.ivmiit.models.DBCredentialData;
import ru.ivmiit.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements BaseRepository<Long, User> {

    public static void main(String[] args) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = new User();
        user.setName("azat");
        user.setPasswordHash("Password_hash");
        user.setSessionID("asd");
    }
    private static UserRepositoryImpl userRepository;

    private static String tableName = "user";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    private UserRepositoryImpl(){
        try {
            connection = DriverManager.getConnection(DBCredentialData.getURL(),DBCredentialData.getUsername(), DBCredentialData.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserRepositoryImpl getInstance(){
        if(userRepository == null){
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }

    @Override
    public User getById(Long id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?;");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return null;
            }
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPasswordHash(resultSet.getString("passwordhash"));
            user.setSessionID(resultSet.getString("sessionid"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll(){
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
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByNameAndPassword(String user, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"" + tableName + "\" WHERE name = ? AND passwordhash = ?;");
        preparedStatement.setString(1,user);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            User userObj = new User();
            userObj.setId(resultSet.getLong("id"));
            userObj.setName(resultSet.getString("name"));
            userObj.setPasswordHash(resultSet.getString("passwordhash"));
            userObj.setSessionID(resultSet.getString("sessionid"));
            return userObj;
        }else{
            return null;
        }
    }
    @Override
    public void save(User obj) throws SQLException {
        PreparedStatement preparedStatement;

        if(obj.getId() == null){
                preparedStatement = connection.prepareStatement("INSERT INTO \"user\" (name, passwordhash, sessionid) VALUES (?,?,?);");
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getPasswordHash());
                preparedStatement.setString(3, obj.getSessionID());
                preparedStatement.execute();
        }else {
                preparedStatement = connection.prepareStatement("INSERT INTO \"user\" (id, name, passwordhash, sessionid) VALUES (?,?,?,?);");
                preparedStatement.setLong(1, obj.getId());
                preparedStatement.setString(2, obj.getName());
                preparedStatement.setString(3, obj.getPasswordHash());
                preparedStatement.setString(4, obj.getSessionID());
                preparedStatement.execute();
        }


    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"user\" WHERE id = ?;");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
