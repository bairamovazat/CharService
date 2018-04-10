package ru.ivmiit.repositories;

import ru.ivmiit.models.BaseModel;

import java.util.*;

public class BaseRepositoryImpl<T extends BaseModel<Long>> implements BaseRepository<Long, T> {

    public static void main(String[] args) {
    }

    private HashMap<Long, T> dataHashMap;
    private Long lastId;

    public BaseRepositoryImpl(){
        this.lastId = 1L;
        this.dataHashMap = new HashMap<>();
    }

    @Override
    public T getById(Long id) {
        return dataHashMap.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(dataHashMap.values());
    }

    @Override
    public void save(T obj) {
        if(obj.getId() == null){
            dataHashMap.put(++lastId,obj);
        }else{
            dataHashMap.put(obj.getId(),obj);
        }
    }

    @Override
    public void delete(Long id) {
        dataHashMap.remove(id);
    }
}
