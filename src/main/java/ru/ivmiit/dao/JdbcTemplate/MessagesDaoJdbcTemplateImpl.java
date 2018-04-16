package ru.ivmiit.dao.JdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.MessagesDao;
import ru.ivmiit.models.Message;
import ru.ivmiit.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MessagesDaoJdbcTemplateImpl implements MessagesDao {

    private JdbcTemplate jdbcTemplate;

    private String tableName = "message";
    private String userTableName = "user";

    //language=SQL
    private String SQL_INSERT = "INSERT INTO \"" + tableName + "\" (text, from_user, for_user, send_date, is_read) VALUES (?,?,?,?,?);";
    //language=SQL
    private String SQL_UPDATE = "UPDATE \"" + tableName + "\" SET text = ?, from_user = ?, for_user = ?, send_date = ?, is_read = ? WHERE id = ?;";
    //language=SQL
    private String SQL_DELETE = "DELETE FROM \"" + tableName + "\" WHERE id = ?";
    //language=SQL
    private String SQL_SELECT_ALL_MESSAGE_WHERE_FROM_USER_AND_FOR_USER = "SELECT message.id as message_id, text, send_date, is_read, from_user.id as from_user_id, from_user.name as from_user_name, for_user.id as for_user_id, for_user.name as for_user_name FROM \"" + tableName + "\" AS message LEFT JOIN \"" + userTableName + "\" AS from_user ON from_user = from_user.id LEFT JOIN \"" + userTableName + "\" AS for_user ON message.for_user = for_user.id WHERE from_user = ? or for_user = ? ORDER BY send_date;";
    //language=SQL
    private String SQL_SELECT_ALL_MESSAGE = "SELECT message.id as message_id, text, send_date, is_read, from_user.id as from_user_id, from_user.name as from_user_name, for_user.id as for_user_id, for_user.name as for_user_name FROM \"" + tableName + "\" AS message LEFT JOIN \"" + userTableName + "\" AS from_user ON from_user = from_user.id LEFT JOIN \"" + userTableName + "\" AS for_user ON message.for_user = for_user.id ORDER BY send_date;";
    //language=SQL
    private String SQL_SELECT_MESSAGE_WHERE_ID = "SELECT message.id as message_id, text, send_date, is_read, from_user.id as from_user_id, from_user.name as from_user_name, for_user.id as for_user_id, for_user.name as for_user_name FROM \"" + tableName + "\" AS message LEFT JOIN \"" + userTableName + "\" AS from_user ON from_user = from_user.id LEFT JOIN \"" + userTableName + "\" AS for_user ON message.for_user = for_user.id WHERE message.id = ? ORDER BY send_date;";

    private RowMapper<Message> rowMapper = (resultSet, i) ->{
        User fromUser = User.builder()
                .id(resultSet.getLong("from_user_id"))
                .name(resultSet.getString("from_user_name"))
                .build();

        User forUser = User.builder()
                .id(resultSet.getLong("for_user_id"))
                .name(resultSet.getString("for_user_name"))
                .build();

        return Message.builder()
                .id(resultSet.getLong("message_id"))
                .text(resultSet.getString("text"))
                .sendDate(new Date(resultSet.getLong("send_date")))
                .isRead(resultSet.getBoolean("is_read"))
                .forUser(forUser)
                .fromUser(fromUser)
                .build();
    };



    public MessagesDaoJdbcTemplateImpl(DriverManagerDataSource driverManagerDataSource){
        this.jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
    }

    @Override
    public Optional<Message> find(Long id) {
        List<Message> messages = this.jdbcTemplate.query(SQL_SELECT_MESSAGE_WHERE_ID,rowMapper,id);
        if(messages.size() == 0){
            return Optional.empty();
        }
        return Optional.of(messages.get(0));
    }

    @Override
    public void save(Message obj) {
        if(obj.getId() != null){
            update(obj);
            return;
        }
        this.jdbcTemplate.update(SQL_INSERT, obj.getText(), obj.getFromUser().getId(), obj.getForUser().getId(), obj.getSendDate().getTime(), obj.isRead());
    }

    @Override
    public void update(Message obj) {
        this.jdbcTemplate.update(SQL_UPDATE, obj.getText(), obj.getFromUser().getId(), obj.getForUser().getId(), obj.getSendDate().getTime(), obj.isRead(), obj.getId());
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public List<Message> findAll() {
        return this.jdbcTemplate.query(SQL_SELECT_ALL_MESSAGE,rowMapper);
    }

    @Override
    public List<Message> getMessagesByUser(User user) {
        return this.jdbcTemplate.query(SQL_SELECT_ALL_MESSAGE_WHERE_FROM_USER_AND_FOR_USER,rowMapper,user.getId(),user.getId());
    }

    @Override
    public List<Message> getMessagesByUserId(Long id) {
        return getMessagesByUser(User.builder().id(id).build());
    }
}
