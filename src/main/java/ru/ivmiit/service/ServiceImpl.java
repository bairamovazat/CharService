package ru.ivmiit.service;

import ru.ivmiit.dao.BaseDao;
import ru.ivmiit.dao.CrudDao;
import ru.ivmiit.dao.ProductDao;
import ru.ivmiit.dao.UserDao;

public class ServiceImpl implements Service {
    private CrudDao productRepository;
    private CrudDao userRepository;
    private AuthService authService;

    private static ServiceImpl serviceImplInstance;

    private ServiceImpl(){
        productRepository = ProductDao.getInstance();
        userRepository = UserDao.getInstance();
        authService = AuthServiceImpl.getInstance();
    }

    private void createBaseDao(){
        productRepository = new BaseDao();
        userRepository = UserDao.getInstance();
        authService = AuthServiceImpl.getInstance();
    }
    public static ServiceImpl getInstance(){
        if(serviceImplInstance == null){
            serviceImplInstance = new ServiceImpl();
        }
        return serviceImplInstance;
    }

    @Override
    public CrudDao getProductRepository() {
        return productRepository;
    }

    @Override
    public CrudDao getUserRepository() {
        return userRepository;
    }

    @Override
    public AuthService getAuthService() {
        return authService;
    }
}
