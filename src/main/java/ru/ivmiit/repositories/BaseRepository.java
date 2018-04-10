package ru.ivmiit.repositories;

import ru.ivmiit.models.BaseModel;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<L,T extends BaseModel<L>> {
    T getById(L id);

    List<T> getAll();

    void save(T obj) throws SQLException;
    
    void delete(L id);
}
