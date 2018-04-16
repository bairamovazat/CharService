package ru.ivmiit.service;

import ru.ivmiit.dao.MessagesDao;
import ru.ivmiit.dao.UsersDao;

import java.util.Properties;

public interface Service {
    MessagesDao getMessagesRepository();
    UsersDao getUsersRepository();
    AuthService getAuthService();
    Properties getProperties();

    RegistrationService getRegistrationService();
}
