package ru.ivmiit.repositories;

import ru.ivmiit.models.Category;
import ru.ivmiit.models.DBCredentialData;
import ru.ivmiit.models.Product;
import ru.ivmiit.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements BaseRepository<Long,Product> {
    private static ProductRepositoryImpl productRepository;

    private static String tableName = "product";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    private ProductRepositoryImpl(){
        try {
            connection = DriverManager.getConnection(DBCredentialData.getURL(),DBCredentialData.getUsername(), DBCredentialData.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductRepositoryImpl getInstance(){
        if(productRepository == null){
            productRepository = new ProductRepositoryImpl();
        }
        return productRepository;
    }

    @Override
    public Product getById(Long id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"product\" WHERE id = ?;");
            preparedStatement.setLong(1,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll(){
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName + ";");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setCategory(Category.valueOf(resultSet.getString("category")));
                product.setName(resultSet.getString("name"));
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }
            return products;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Product obj){
        PreparedStatement preparedStatement;

        if(obj.getId() == null){
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO \"product\" (name, category) VALUES (?,?);");
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getCategory().toString());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO \"product\" (id, name, category) VALUES (?,?,?);");
                preparedStatement.setLong(1, obj.getId());
                preparedStatement.setString(2, obj.getName());
                preparedStatement.setString(3, obj.getCategory().toString());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"product\" WHERE id = ?;");
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
