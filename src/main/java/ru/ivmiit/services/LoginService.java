package ru.ivmiit.services.rest;

import ru.ivmiit.forms.LoginForm;
import ru.ivmiit.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
