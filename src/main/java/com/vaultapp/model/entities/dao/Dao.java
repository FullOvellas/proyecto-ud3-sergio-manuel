package com.vaultapp.model.entities.dao;

import java.util.List;

public interface Dao<T> {

    void create(T t);

    List<T> read();

    void delete(Long id);

}
