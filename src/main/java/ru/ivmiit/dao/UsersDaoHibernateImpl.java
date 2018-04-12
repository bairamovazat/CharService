package ru.ivmiit.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ivmiit.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersDaoHibernateImpl implements UsersDao {

    public static void main(String[] args) {

    }
    public UsersDaoHibernateImpl(DataSource dataSource){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "");
        configuration.setProperty("hibernate.connection.url","");
        configuration.setProperty("hibernate.connection.username","");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.connection.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

    }
    @Override
    public Optional<User> getUserByNameAndPassword(String user, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserBySessionId(String sessionId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
