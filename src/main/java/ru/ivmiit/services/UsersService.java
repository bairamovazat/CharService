package ru.ivmiit.services.rest;

import ru.ivmiit.forms.UserForm;
import ru.ivmiit.models.User;

import java.util.List;

public interface UsersService {
    void signUp(UserForm userForm);

    List<User> findAll();

    User findOne(Long userId);
}
