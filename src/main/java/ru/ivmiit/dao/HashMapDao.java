package ru.ivmiit.dao;

import java.util.*;

public class HashMapDao implements CrudDao<Long, String> {
    private HashMap<Long, String> dataHashMap;
    private Long lastId;

    public HashMapDao() {
        this.lastId = 1L;
        this.dataHashMap = new HashMap<>();
    }

    @Override
    public Optional<String> find(Long id) {
        return Optional.of(dataHashMap.get(id));
    }

    @Override
    public List<String> findAll() {
        return new ArrayList<>(dataHashMap.values());
    }

    @Override
    public void save(String obj) {
        dataHashMap.put(++lastId, obj);
    }

    @Override
    public void update(String obj) {
        for(Long id : dataHashMap.keySet()){
            if(dataHashMap.get(id).equals(obj)){
                dataHashMap.put(id,obj);
            }
        }
    }

    @Override
    public void delete(Long id) {
        dataHashMap.remove(id);
    }
}
