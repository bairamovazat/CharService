package ru.ivmiit.dao;

import ru.ivmiit.models.User;

import java.util.Optional;

public interface UsersDao extends CrudDao<Long, User>{
    Optional<User> getUserByNameAndPassword(String user, String password);
    Optional<User> getUserByName(String user);
    Optional<User> getUserBySessionId(String sessionId);
}
