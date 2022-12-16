package com.vaultapp.model.repository;

import com.vaultapp.model.entities.FilmVault;
import com.vaultapp.model.entities.dao.Dao;
import com.vaultapp.model.entities.dao.FilmVaultDao;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FilmVaultDbRepository implements Repository<FilmVault> {
    private EntityManager em;
    private Dao<FilmVault> filmVaultDao;
    private static FilmVaultDbRepository instance;

    static { instance = new FilmVaultDbRepository(JpaUtil.getEntityManager()); }

    private FilmVaultDbRepository(EntityManager entityManager) {
        em = entityManager;
        filmVaultDao = FilmVaultDao.getInstance();
    }

    public static FilmVaultDbRepository getInstance() { return instance; }

    @Override
    public void add(FilmVault filmVault) {
        try {
            em.getTransaction().begin();
            filmVaultDao.create(filmVault);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<FilmVault> getAsList() {
        return filmVaultDao.read();
    }

    @Override
    public void remove(Long id) {
        try {
            em.getTransaction().begin();
            filmVaultDao.delete(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
