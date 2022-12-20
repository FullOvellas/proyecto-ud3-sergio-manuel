package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.User;
import com.vaultapp.utilities.filters.BookFilter;

import java.util.List;

public interface Dao<T> {

    void create(T t);

    List<T> read();

    void delete(Long id);

}
