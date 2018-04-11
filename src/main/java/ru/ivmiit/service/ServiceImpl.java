package ru.ivmiit.service;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.*;
import ru.ivmiit.models.DBCredentialData;

public class ServiceImpl implements Service {
    private static ServiceImpl serviceImplInstance;

    private ProductsDao productRepository;
    private UsersDao userRepository;
    private AuthService authService;

//    private ServiceImpl() {
//        //Не создаётся java.lang.NoClassDefFoundError: org/springframework/jdbc/datasource/DriverManagerDataSource
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName(DBCredentialData.getClassName());
//        driverManagerDataSource.setUrl(DBCredentialData.getURL());
//        driverManagerDataSource.setUsername(DBCredentialData.getUsername());
//        driverManagerDataSource.setPassword(DBCredentialData.getPassword());
//        productRepository = ProductsDaoImpl.getInstance();
//        userRepository = new UsersDaoJdbcTemplateImpl(driverManagerDataSource);
//        authService = AuthServiceImpl.getInstance();
//    }
    private ServiceImpl() {
        productRepository = ProductsDaoImpl.getInstance();
        userRepository = new UsersDaoHibernateImpl();
        authService = AuthServiceImpl.getInstance();
    }


    public static ServiceImpl getInstance() {
        if (serviceImplInstance == null) {
            serviceImplInstance = new ServiceImpl();
        }
        return serviceImplInstance;
    }

    @Override
    public ProductsDao getProductRepository() {
        return productRepository;
    }

    @Override
    public UsersDao getUserRepository() {
        return userRepository;
    }

    @Override
    public AuthService getAuthService() {
        return authService;
    }
}
