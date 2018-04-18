package ru.ivmiit;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.JdbcTemplate.MessagesDaoJdbcTemplateImpl;
import ru.ivmiit.dao.JdbcTemplate.UsersDaoJdbcTemplateImpl;
import ru.ivmiit.dao.MessagesDao;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.service.*;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "ru.ivmiit")
public class AppConfig {

    private DriverManagerDataSource driverManagerDataSource;

    @SneakyThrows
    public AppConfig(){
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/db.properties"));
        driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(properties.getProperty("db.driverClassName"));
        driverManagerDataSource.setUrl(properties.getProperty("db.url"));
        driverManagerDataSource.setUsername(properties.getProperty("db.username"));
        driverManagerDataSource.setPassword(properties.getProperty("db.password"));
    }

    @Bean
    public UsersDao usersDao(){
        return new UsersDaoJdbcTemplateImpl(driverManagerDataSource);
    }

    @Bean
    public MessagesDao messagesDao(){
        return new MessagesDaoJdbcTemplateImpl(driverManagerDataSource);
    }
}
