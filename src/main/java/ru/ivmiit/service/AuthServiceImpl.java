package ru.ivmiit.service;

import ru.ivmiit.models.User;
import ru.ivmiit.dao.UserDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {
    private static String authCookieName = "NotAuthCookie";
    private static AuthServiceImpl authServiceImplInstance;
    private UserDao userRepository;

    private AuthServiceImpl(){
        userRepository = UserDao.getInstance();
    }

    public static AuthServiceImpl getInstance(){
        if(authServiceImplInstance == null){
            authServiceImplInstance = new AuthServiceImpl();
        }
        return authServiceImplInstance;
    }

    @Override
    public Optional<User> getUserByRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Optional<User> user = Optional.empty();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(authCookieName)){
                user = userRepository.getUserBySessionId(cookie.getValue());
            }
        }
        return user;
    }

    @Override
    public void authorizationByUser(User user, HttpServletResponse response) {
        Service service = ServiceImpl.getInstance();
        UserDao userRepository = (UserDao)service.getUserRepository();
        String uuid = UUID.randomUUID().toString();
        user.setSessionID(uuid);
        userRepository.update(user);
        Cookie cookie = new Cookie(authCookieName,uuid);
        response.addCookie(cookie);
    }

    @Override
    public void logout( HttpServletResponse response) {
        Cookie cookie = new Cookie(authCookieName, "");
        response.addCookie(cookie);
    }
}
