package ru.ivmiit.services;

import org.springframework.transaction.annotation.Transactional;
import ru.ivmiit.forms.UserRegistrationForm;
import ru.ivmiit.models.User;

/**
 * 10.11.2017
 * RegistrationService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface RegistrationService {
    void register(UserRegistrationForm userForm);

    @Transactional
    void setNewPassword(User user, String password);
}
