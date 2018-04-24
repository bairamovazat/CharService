package ru.ivmiit.services;

import ru.ivmiit.forms.UserRegistrationForm;

/**
 * 10.11.2017
 * RegistrationService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface RegistrationService {
    void register(UserRegistrationForm userForm);
}
