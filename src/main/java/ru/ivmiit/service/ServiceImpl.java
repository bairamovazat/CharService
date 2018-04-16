package ru.ivmiit.service;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.*;
import ru.ivmiit.dao.JdbcTemplate.MessagesDaoJdbcTemplateImpl;
import ru.ivmiit.dao.JdbcTemplate.UsersDaoJdbcTemplateImpl;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ServiceImpl implements Service {
    private static ServiceImpl serviceImplInstance;

    private MessagesDao messagesRepository;
    private UsersDao userRepository;
    private AuthService authService;
    private Properties properties;
    private RegistrationService registrationService;

    @SneakyThrows
    private ServiceImpl() {
        properties = new Properties();
        properties.load(getClass().getResourceAsStream("/db.properties"));

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(properties.getProperty("db.driverClassName"));
        driverManagerDataSource.setUrl(properties.getProperty("db.url"));
        driverManagerDataSource.setUsername(properties.getProperty("db.username"));
        driverManagerDataSource.setPassword(properties.getProperty("db.password"));

        messagesRepository = new MessagesDaoJdbcTemplateImpl(driverManagerDataSource);
        userRepository = new UsersDaoJdbcTemplateImpl(driverManagerDataSource);
        authService = new AuthServiceImpl(userRepository);
        registrationService = new RegistrationServiceImpl(userRepository);
    }

    public static ServiceImpl getInstance() {
        if (serviceImplInstance == null) {
            serviceImplInstance = new ServiceImpl();
        }
        return serviceImplInstance;
    }

    @Override
    public MessagesDao getMessagesRepository() {
        return messagesRepository;
    }

    @Override
    public UsersDao getUsersRepository() {
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

    @Override
    public RegistrationService getRegistrationService(){
        return registrationService;
    }
}
