package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.FilmVault;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class FilmVaultDao implements Dao<FilmVault> {
    private EntityManager em;

    private static FilmVaultDao instance;

    static {
        instance = new FilmVaultDao(JpaUtil.getEntityManager());
    }

    public static FilmVaultDao getInstance() {
        return instance;
    }

    public FilmVaultDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(FilmVault filmVault) {
        if (filmVault.getId() != null && filmVault.getId() > 0) {
            em.merge(filmVault);
        } else {
            em.persist(filmVault);
        }
    }

    @Override
    public List<FilmVault> read() {
        return em.createQuery("select f from FilmVault f", FilmVault.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        if (em.find(FilmVault.class, id) != null) {
            em.remove(em.find(FilmVault.class, id));
        }
    }
}
