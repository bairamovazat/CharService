package ru.ivmiit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthServiceImpl implements AuthService {
    private static String authCookieName = "NotAuthCookie";

    @Autowired
    private UsersDao usersRepository;

    public AuthServiceImpl(){}

    @Deprecated
    public AuthServiceImpl(UsersDao usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<User> getUserByRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Optional<User> user = Optional.empty();
        if(cookies == null){
            return user;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(authCookieName)){
                user = usersRepository.getUserBySessionId(cookie.getValue());
            }
        }
        return user;
    }

    @Override
    public void authorizationByUser(User user, HttpServletResponse response) {
        String uuid = UUID.randomUUID().toString();
        user.setSessionId(uuid);
        usersRepository.update(user);
        Cookie cookie = new Cookie(authCookieName,uuid);
        response.addCookie(cookie);
    }

    @Override
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(authCookieName, "");
        response.addCookie(cookie);
    }
}
