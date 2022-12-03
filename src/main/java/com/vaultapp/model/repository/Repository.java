package com.vaultapp.model.repository;

import java.util.List;

public interface Repository<T> {

    void add(T t);

    List<T> getAsList();

    void remove(Long id);

}
