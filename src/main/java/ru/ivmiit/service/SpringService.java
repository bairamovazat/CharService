package ru.ivmiit.service;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.ivmiit.AppConfig;
import ru.ivmiit.dao.*;

//Один большой костыль!!!
//Как автоварить классы в сервлете ? ставлю просто autowired не работает
public class SpringService implements Service {
    private static SpringService serviceImplInstance;

    private ApplicationContext context;

    @SneakyThrows
    private SpringService() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Deprecated
    public static SpringService getInstance() {
        if (serviceImplInstance == null) {
            serviceImplInstance = new SpringService();
        }
        return serviceImplInstance;
    }

    @Override
    public MessagesDao getMessagesRepository() {
        return context.getBean(MessagesDao.class);
    }

    @Override
    public UsersDao getUsersRepository() {
        return context.getBean(UsersDao.class);
    }

    @Override
    public AuthService getAuthService() {
        return context.getBean(AuthService.class);
    }

    @Override
    public RegistrationService getRegistrationService(){
        return context.getBean(RegistrationService.class);
    }
}
