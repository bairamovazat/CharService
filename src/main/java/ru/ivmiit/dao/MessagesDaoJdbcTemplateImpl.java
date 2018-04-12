package ru.ivmiit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.models.Message;

import java.util.List;
import java.util.Optional;

public class MessagesDaoJdbcTemplateImpl implements MessagesDao {

    private JdbcTemplate jdbcTemplate;

    public MessagesDaoJdbcTemplateImpl(DriverManagerDataSource driverManagerDataSource){
        this.jdbcTemplate = new JdbcTemplate(driverManagerDataSource);

    }
    @Override
    public Optional<Message> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Message obj) {

    }

    @Override
    public void update(Message obj) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Message> findAll() {
        return null;
    }
}
