package ru.ivmiit.service;

import ru.ivmiit.models.User;

public interface RegistrationService {
    void registerUser(User user) throws IllegalArgumentException;

    boolean userIsRegistered(String userName);
}
