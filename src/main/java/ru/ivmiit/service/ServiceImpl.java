package ru.ivmiit.service;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.*;

import java.io.FileInputStream;
import java.util.Properties;

public class ServiceImpl implements Service {
    private static ServiceImpl serviceImplInstance;

    private MessagesDao messagesDao;
    private UsersDao userRepository;
    private AuthService authService;
    private Properties properties;

    @SneakyThrows
    private ServiceImpl() {
        properties = new Properties();
        properties.load(getClass().getResourceAsStream("db.properties"));

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(properties.getProperty("db.driverClassName"));
        driverManagerDataSource.setUrl(properties.getProperty("db.url"));
        driverManagerDataSource.setUsername(properties.getProperty("db.username"));
        driverManagerDataSource.setPassword(properties.getProperty("db.password"));

        messagesDao = new MessagesDaoJdbcTemplateImpl(driverManagerDataSource);
        userRepository = new UsersDaoJdbcTemplateImpl(driverManagerDataSource);
        authService = new AuthServiceImpl(userRepository);
    }

    public static ServiceImpl getInstance() {
        if (serviceImplInstance == null) {
            serviceImplInstance = new ServiceImpl();
        }
        return serviceImplInstance;
    }

    @Override
    public MessagesDao getMessagesDao() {
        return messagesDao;
    }

    @Override
    public UsersDao getUserRepository() {
        return userRepository;
    }

    @Override
    public AuthService getAuthService() {
        return authService;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }
}
