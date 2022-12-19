package com.vaultapp.model.repository;

import com.vaultapp.model.entities.User;
import com.vaultapp.utilities.filters.BookFilter;

import java.util.List;

public interface Repository<T> {

    void add(T t);

    List<T> getAsList();

    void remove(Long id);

}
