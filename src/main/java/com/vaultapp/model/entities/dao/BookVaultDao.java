package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.BookVault;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookVaultDao implements Dao<BookVault> {
    private EntityManager em;

    private static BookVaultDao instance;

    static {
        instance = new BookVaultDao(JpaUtil.getEntityManager());
    }

    public static BookVaultDao getInstance() {
        return instance;
    }

    public BookVaultDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(BookVault bookVault) {
        if (bookVault.getId() != null && bookVault.getId() > 0) {
            em.merge(bookVault);
        } else {
            em.persist(bookVault);
        }
    }

    @Override
    public List<BookVault> read() {
        return em.createQuery("select b from BookVault b", BookVault.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        if (em.find(BookVault.class, id) != null) {
            em.remove(em.find(BookVault.class, id));
        }
    }

}
