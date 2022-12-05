package com.vaultapp.model.repository;

import com.vaultapp.model.entities.dao.Dao;
import com.vaultapp.model.entities.dao.FilmDao;
import com.vaultapp.model.entities.Film;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FilmRepository implements Repository<Film>{
    private EntityManager em;
    private Dao<Film> filmDao;
    private static FilmRepository instance;

    static { instance = new FilmRepository(JpaUtil.getEntityManager()); }

    private FilmRepository(EntityManager entityManager) {
        em = entityManager;
        filmDao = FilmDao.getInstance();
    }

    public static FilmRepository getInstance() { return instance; }

    @Override
    public void add(Film film) {
        try {
            em.getTransaction().begin();
            filmDao.create(film);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Film> getAsList() {
        return filmDao.read();
    }

    @Override
    public void remove(Long id) {
        try {
            em.getTransaction().begin();
            filmDao.delete(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
