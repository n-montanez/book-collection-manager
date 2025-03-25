package com.globant.model.dao.generic;

import java.util.List;

public interface GenericDAO<T> {
    void save(T object);
    void update(T object);
    void delete(T object);
    T findById(Long id);
    List<T> findAll();
}
