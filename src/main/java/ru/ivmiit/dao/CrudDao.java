package ru.ivmiit.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<L, T> {
    Optional<T> find(L id);
    void save(T obj);
    void update(T obj);
    void delete(L id);

    //Нужно ли листы делать optional ?
    List<T> findAll();


}
