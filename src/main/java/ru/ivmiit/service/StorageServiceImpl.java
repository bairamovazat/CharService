package ru.ivmiit.service;

import ru.ivmiit.models.Category;
import ru.ivmiit.models.Product;
import ru.ivmiit.models.User;
import ru.ivmiit.repositories.BaseRepository;
import ru.ivmiit.repositories.BaseRepositoryImpl;
import ru.ivmiit.repositories.ProductRepositoryImpl;
import ru.ivmiit.repositories.UserRepositoryImpl;

public class StorageServiceImpl implements StorageService {
    private BaseRepository<Long, Product> productRepository;
    private BaseRepository<Long, User> userRepository;

    private static StorageServiceImpl storageServiceImplInstance;

    private StorageServiceImpl(){
        productRepository = ProductRepositoryImpl.getInstance();
        userRepository = UserRepositoryImpl.getInstance();

    }

    public static StorageServiceImpl getInstance(){
        if(storageServiceImplInstance == null){
            storageServiceImplInstance = new StorageServiceImpl();
        }
        return storageServiceImplInstance;
    }

    @Override
    public BaseRepository getProductRepository() {
        return productRepository;
    }

    @Override
    public BaseRepository getUserRepository() {
        return userRepository;
    }
}
