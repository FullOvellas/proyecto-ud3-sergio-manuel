package com.vaultapp.model.dao;

import java.util.List;

public interface Dao<T> {

    void create(T t);

    List<T> read();

    void delete(Long id);

}
