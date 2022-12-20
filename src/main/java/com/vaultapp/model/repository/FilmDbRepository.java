package com.vaultapp.model.repository;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.User;
import com.vaultapp.model.entities.dao.BookDao;
import com.vaultapp.model.entities.dao.Dao;
import com.vaultapp.model.entities.dao.FilmDao;
import com.vaultapp.model.entities.Film;
import com.vaultapp.utilities.JpaUtil;
import com.vaultapp.utilities.filters.BookFilter;
import com.vaultapp.utilities.filters.FilmFilter;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class FilmDbRepository implements Repository<Film> {
    private EntityManager em;
    private Dao<Film> filmDao;
    private static FilmDbRepository instance;

    static {
        instance = new FilmDbRepository(JpaUtil.getEntityManager());
    }

    private FilmDbRepository(EntityManager entityManager) {
        em = entityManager;
        filmDao = FilmDao.getInstance();
    }

    public static FilmDbRepository getInstance() {
        return instance;
    }

    @Override
    public void add(Film film) {
        try {
            em.getTransaction().begin();
            filmDao.create(film);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public Film findByTmid(String tmid) {
        if (getAsList() == null || getAsList().isEmpty()) {
            return null;
        }
        List<Film> l = getAsList().stream().filter(s -> String.valueOf(s.getTmdbId()).equals(tmid.trim())).collect(Collectors.toList());
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
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

    public List<Film> findBy(User user, FilmFilter filter, String arg) {
        return ((FilmDao) filmDao).findBy(user, filter, arg);
    }
}
