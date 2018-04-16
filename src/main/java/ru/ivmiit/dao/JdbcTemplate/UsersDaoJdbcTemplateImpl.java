package ru.ivmiit.dao.JdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcTemplateImpl implements UsersDao {

    private JdbcTemplate jdbcTemplate;

    private String tableName = "user";
    //language=SQL
    private String SQL_FIND= "SELECT * FROM \"" + tableName + "\" WHERE id=?;";
    //language=SQL
    private String SQL_SELECT_ALL = "SELECT * FROM \"" + tableName + "\";";
    //language=SQL
    private String SQL_SELECT_BY_NAME_AND_PASSWORD = "SELECT * FROM \"" + tableName + "\" WHERE name = ? AND password_hash = ?";
    //language=SQL
    private String SQL_SELECT_BY_NAME = "SELECT * FROM \"" + tableName + "\" WHERE name = ?";
    //language=SQL
    private String SQL_SELECT_BY_SESSIONID = "SELECT * FROM \"" + tableName + "\" WHERE session_id = ?";
    //language=SQL
    private String SQL_INSERT = "INSERT INTO \"" + tableName + "\" (name, password_hash, session_id) VALUES (?,?,?);";
    //language=SQL
    private String SQL_UPDATE = "UPDATE \"" + tableName + "\" SET name = ?, password_hash = ?, session_id = ? WHERE id = ?;";
    //language=SQL
    private String SQL_DELETE = "DELETE FROM  \"" + tableName + "\" WHERE id = ?;";

    private HashMap<Long, User> usersHashMap = new HashMap<>();

    private RowMapper<User> userRowMapper
            = ((resultSet, i) ->
            new User(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("session_id"),
                    resultSet.getString("password_hash"))
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
    public Optional<User> getUserByName(String userName) {
        List<User> users = jdbcTemplate.query(SQL_SELECT_BY_NAME, userRowMapper, userName);
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
        List<User> users = jdbcTemplate.query(SQL_FIND, userRowMapper, id);
        if(users.size() == 0){
            return Optional.empty();
        }
        return Optional.of(users.get(0));
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
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
