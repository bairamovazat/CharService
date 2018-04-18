package ru.ivmiit.service;
import ru.ivmiit.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
public interface AuthService {
    Optional<User> authenticateUserByRequest(HttpServletRequest request);
    void authorizationByUser(User user, HttpServletResponse response);
    void logout(HttpServletResponse response);

    Optional<User> authorizationUserByLoginAndPassword(String login, String password, HttpServletResponse response);
}
