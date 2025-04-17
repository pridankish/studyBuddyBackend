package org.example.diplomabackend.service;

import java.util.List;

public interface IService<T, K> {

    T addNew(T object);

    T getById(K id);

    T update(T object, K id);

    List<T> getAll();

    void delete(K id);
}