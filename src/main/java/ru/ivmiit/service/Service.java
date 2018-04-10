package ru.ivmiit.service;

import ru.ivmiit.dao.CrudDao;

public interface Service {
    CrudDao getProductRepository();
    CrudDao getUserRepository();
    AuthService getAuthService();
}
