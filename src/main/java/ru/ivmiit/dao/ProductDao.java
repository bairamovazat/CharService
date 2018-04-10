package ru.ivmiit.dao;

import lombok.SneakyThrows;
import ru.ivmiit.models.Category;
import ru.ivmiit.models.DBCredentialData;
import ru.ivmiit.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao implements CrudDao<Long, Product> {
    private static ProductDao productRepository;

    private static String tableName = "product";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    private ProductDao() {
        try {
            connection = DriverManager.getConnection(DBCredentialData.getURL(), DBCredentialData.getUsername(), DBCredentialData.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductDao getInstance() {
        if (productRepository == null) {
            productRepository = new ProductDao();
        }
        return productRepository;
    }

    @Override
    @SneakyThrows
    public Optional<Product> find(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"" + tableName + "\" WHERE id = ?;");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
            return Optional.empty();
        }
        Product product = Product.builder()
                .id(resultSet.getLong("id"))
                .category(Category.valueOf(resultSet.getString("category")))
                .name(resultSet.getString("name"))
                .build();
        return Optional.of(product);
    }

    @Override
    public List<Product> findAll() {
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"" + tableName + "\";");
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
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SneakyThrows
    public void save(Product obj) {
        PreparedStatement preparedStatement;
        if (obj.getId() == null) {
            preparedStatement = connection.prepareStatement("INSERT INTO \"" + tableName + "\" (name, category) VALUES (?,?);");
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getCategory().toString());
            preparedStatement.execute();
        } else {
            preparedStatement = connection.prepareStatement("INSERT INTO \"" + tableName + "\" (id, name, category) VALUES (?,?,?);");
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setString(3, obj.getCategory().toString());
            preparedStatement.execute();
        }
    }

    @Override
    @SneakyThrows
    public void update(Product obj) {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE \"" + tableName + "\" SET name = ?, category = ? WHERE id = ?;");
        preparedStatement.setString(1, obj.getName());
        preparedStatement.setString(2, obj.getCategory().toString());
        preparedStatement.setLong(3, obj.getId());
        preparedStatement.execute();
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"" + tableName + "\" WHERE id = ?;");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
    }
}
