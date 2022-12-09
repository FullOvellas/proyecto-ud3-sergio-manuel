package com.vaultapp.model.repository;

import com.vaultapp.model.entities.dao.BookDao;
import com.vaultapp.model.entities.dao.Dao;
import com.vaultapp.model.entities.Book;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class BookDbRepository implements Repository<Book> {
    private EntityManager em;
    private Dao<Book> bookDao;
    private static BookDbRepository instance;

    static { instance = new BookDbRepository(JpaUtil.getEntityManager()); }

    private BookDbRepository(EntityManager entityManager) {
        em = entityManager;
        bookDao = BookDao.getInstance();
    }

    public static BookDbRepository getInstance() { return instance; }

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

    public Book findByIsbn(String isbn) {
        if (getAsList() == null || getAsList().isEmpty()) {
            return null;
        }
        List<Book> l = getAsList().stream().filter(s -> s.getIsbn().equals(isbn.trim())).collect(Collectors.toList());
        if (l == null || l.isEmpty()) {
            return null;
        }
        return l.get(0);
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
