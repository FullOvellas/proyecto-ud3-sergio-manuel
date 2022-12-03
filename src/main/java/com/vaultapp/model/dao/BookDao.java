package com.vaultapp.model.dao;

import com.vaultapp.model.entities.Book;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookDao implements Dao<Book> {
    private EntityManager em;

    public BookDao(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void create(Book book) {
        if (book.getId() != null && book.getId() > 0) {
            em.merge(book);
        } else {
            em.persist(book);
        }
    }

    @Override
    public List<Book> read() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        if (em.find(Book.class, id) != null) {
            em.remove(em.find(Book.class, id));
        }
    }
}
