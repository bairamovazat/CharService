package ru.ivmiit.dao.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UsersDaoHibernateImpl implements UsersDao {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(UsersDaoHibernateImpl.class.getResourceAsStream("/db.properties"));

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(properties.getProperty("db.driverClassName"));
        driverManagerDataSource.setUrl(properties.getProperty("db.url"));
        driverManagerDataSource.setUsername(properties.getProperty("db.username"));
        driverManagerDataSource.setPassword(properties.getProperty("db.password"));
        UsersDaoHibernateImpl usersDaoHibernate = new UsersDaoHibernateImpl(properties);
        Optional<User> user = usersDaoHibernate.getUserByNameAndPassword("bairamovazat","3686bairam");
        User userObj = user.get();
        userObj.setSessionId("asdвв");
        usersDaoHibernate.update(userObj);
    }
    private SessionFactory sessionFactory;
    //Изменить использования properties т.к не понятно как там будет всё храниться
    //?? Вопрос как вообще пользоваться правитльно сессиями? как я понял там надо юзать pool
    public UsersDaoHibernateImpl(Properties properties) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", (String) properties.get("db.driverClassName"));
        configuration.setProperty("hibernate.connection.url", (String) properties.get("db.url"));
        configuration.setProperty("hibernate.connection.username", (String) properties.get("db.username"));
        configuration.setProperty("hibernate.connection.password", (String) properties.get("db.password"));
        configuration.setProperty("hibernate.connection.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addResource("hbm/User.hbm.xml");
        sessionFactory = configuration.buildSessionFactory();
    }
    @Override
    public Optional<User> getUserByNameAndPassword(String userName, String password) {
        Session session = sessionFactory.openSession();
        Optional<User> user;
        try {
            user = Optional.of(session.createQuery("from User user where name=:username and passwordHash=:password", User.class)
                    .setParameter("username", userName)
                    .setParameter("password", password)
                    .getSingleResult());
        }catch (NoResultException e){
            user = Optional.empty();
        }
        session.close();
        return user;
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        Session session = sessionFactory.openSession();
        Optional<User> user;
        try {
            user = Optional.of(session.createQuery("from User user where name=:username", User.class)
                    .setParameter("username", userName)
                    .getSingleResult());
        }catch (NoResultException e){
            user = Optional.empty();
        }
        session.close();
        return user;
    }

    @Override
    public Optional<User> getUserBySessionId(String sessionId) {
        Session session = sessionFactory.openSession();
        Optional<User> user;
        try {
            user = Optional.of(session.createQuery("from User user where sessionId=:sessionId", User.class)
                    .setParameter("sessionId", sessionId)
                    .getSingleResult());
        }catch (NoResultException e){
            user = Optional.empty();
        }
        session.close();
        return user;
    }

    @Override
    public Optional<User> find(Long id) {
        Session session = sessionFactory.openSession();
        Optional<User> user;
        try {
            user = Optional.of(session.createQuery("from User user where id=:id", User.class)
                    .setParameter("id", id)
                    .getSingleResult());
        }catch (NoResultException e){
            user = Optional.empty();
        }
        session.close();
        return user;
    }

    @Override
    public void save(User obj) {
        Session session = sessionFactory.openSession();
        session.save(obj);
        session.close();
    }

    @Override
    public void update(User obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.delete(User.builder().id(id).build());
        session.close();
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> users;
        try {
            users = session.createQuery("from User user", User.class).getResultList();
        }catch (NoResultException e){
            users = null;
        }
        session.close();
        return users;
    }
}
