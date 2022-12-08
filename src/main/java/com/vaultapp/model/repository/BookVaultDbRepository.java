package com.vaultapp.model.repository;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.BookVault;
import com.vaultapp.model.entities.dao.BookVaultDao;
import com.vaultapp.model.entities.dao.Dao;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookVaultDbRepository implements Repository<BookVault> {
    private EntityManager em;
    private Dao<BookVault> bookVaultDao;
    private static BookVaultDbRepository instance;

    static { instance = new BookVaultDbRepository(JpaUtil.getEntityManager()); }

    private BookVaultDbRepository(EntityManager entityManager) {
        em = entityManager;
        bookVaultDao = BookVaultDao.getInstance();
    }

    public static BookVaultDbRepository getInstance() { return instance; }

    @Override
    public void add(BookVault book) {
        try {
            em.getTransaction().begin();
            bookVaultDao.create(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<BookVault> getAsList() {
        return bookVaultDao.read();
    }

    @Override
    public void remove(Long id) {
        try {
            em.getTransaction().begin();
            bookVaultDao.delete(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
