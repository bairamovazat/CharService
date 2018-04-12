package ru.ivmiit.service;

import ru.ivmiit.dao.MessagesDao;
import ru.ivmiit.dao.UsersDao;

import java.util.Properties;

public interface Service {
    MessagesDao getMessagesDao();
    UsersDao getUserRepository();
    AuthService getAuthService();
    Properties getProperties();
}
