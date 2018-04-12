package ru.ivmiit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcTemplateImpl implements UsersDao {

    private JdbcTemplate jdbcTemplate;

    private String tableName = "user";
    //language=SQL
    private String SQL_SELECT_ALL = "SELECT * FROM \"" + tableName + "\";";
    //language=SQL
    private String SQL_SELECT_BY_NAME_AND_PASSWORD = "SELECT * FROM \"" + tableName + "\" WHERE name = ? AND passwordhash = ?";
    //language=SQL
    private String SQL_SELECT_BY_SESSIONID = "SELECT * FROM \"" + tableName + "\" WHERE sessionid = ?";
    //language=SQL
    private String SQL_INSERT = "INSERT INTO \"" + tableName + "\" (name, passwordhash, sessionid) VALUES (?,?,?);";
    //language=SQL
    private String SQL_UPDATE = "UPDATE \"" + tableName + "\" SET name = ?, passwordhash = ?, sessionid = ? WHERE id = ?;";

    private HashMap<Long, User> usersHashMap = new HashMap<>();

    private RowMapper<User> userRowMapper
            = ((resultSet, i) ->
            new User(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("sessionid"),
                    resultSet.getString("passwordhash"))
    );


    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> getUserByNameAndPassword(String name, String password) {
        List<User> users = jdbcTemplate.query(SQL_SELECT_BY_NAME_AND_PASSWORD, userRowMapper, name, password);
        if (users.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    @Override
    public Optional<User> getUserBySessionId(String sessionId) {
        List<User> users = jdbcTemplate.query(SQL_SELECT_BY_SESSIONID, userRowMapper, sessionId);
        if (users.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    @Override
    public Optional<User> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User obj) {
        jdbcTemplate.update(SQL_INSERT, obj.getName(), obj.getPasswordHash(), obj.getSessionID());
    }

    @Override
    public void update(User obj) {
        jdbcTemplate.update(SQL_UPDATE, obj.getName(), obj.getPasswordHash(), obj.getSessionID(), obj.getId());
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
