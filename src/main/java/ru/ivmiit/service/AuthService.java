package ru.ivmiit.service;
import ru.ivmiit.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
public interface AuthService {
    Optional<User> getUserByRequest(HttpServletRequest request);
    void authorizationByUser(User user, HttpServletResponse response);
    void logout(HttpServletResponse response);
}
