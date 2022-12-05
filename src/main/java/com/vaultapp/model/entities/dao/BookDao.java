package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.Book;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookDao implements Dao<Book> {
    private EntityManager em;

    private static BookDao instance;

    static {
        instance = new BookDao(JpaUtil.getEntityManager());
    }

    private BookDao(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static BookDao getInstance() { return instance; }

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
