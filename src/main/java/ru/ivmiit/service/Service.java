package ru.ivmiit.service;

import ru.ivmiit.dao.CrudDao;
import ru.ivmiit.dao.ProductsDao;
import ru.ivmiit.dao.UsersDao;

public interface Service {
    ProductsDao getProductRepository();
    UsersDao getUserRepository();
    AuthService getAuthService();
}
