package com.vaultapp.model.repository;

import com.vaultapp.model.dao.BookDao;
import com.vaultapp.model.dao.Dao;
import com.vaultapp.model.entities.Book;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookRepository implements Repository<Book> {
    private EntityManager em;
    private Dao<Book> bookDao;

    public BookRepository(EntityManager entityManager) {
        em = entityManager;
        bookDao = BookDao.getInstance();
    }

    @Override
    public void add(Book book) {
        try {
            em.getTransaction().begin();
            bookDao.create(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAsList() {
        return bookDao.read();
    }

    @Override
    public void remove(Long id) {
        try {
            em.getTransaction().begin();
            bookDao.delete(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
