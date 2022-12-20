package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.Film;
import com.vaultapp.model.entities.User;
import com.vaultapp.utilities.JpaUtil;
import com.vaultapp.utilities.filters.BookFilter;
import com.vaultapp.utilities.filters.FilmFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class FilmDao implements Dao<Film>{
    private EntityManager em;

    private static FilmDao instance;

    static {
        instance = new FilmDao(JpaUtil.getEntityManager());
    }

    private FilmDao(EntityManager entityManager) {
        em = entityManager;
    }

    public static FilmDao getInstance() { return instance; }

    @Override
    public void create(Film film) {
        if (film.getId() != null && film.getId() > 0) {
            em.merge(film);
        } else {
            em.persist(film);
        }
    }

    @Override
    public List<Film> read() {
        return em.createQuery("select f from Film f", Film.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        if (em.find(Film.class, id) != null) {
            em.remove(em.find(Film.class, id));
        }
    }

    public List<Film> findBy(User user, FilmFilter filter, String arg) {
        Query q;

        if (filter == FilmFilter.TITLE) {
            q = em.createQuery("select f from Film f join User u where u = :user and f.title = :title");
            q.setParameter("title", arg);
            q.setParameter("user", user);
        } else if (filter == FilmFilter.TMID) {
            q = em.createQuery("select f from Film f join User u where u = :user and f.tmdbId = :tmid");
            q.setParameter("tmid", arg);
            q.setParameter("user", user);
        } else {
            return null;
        }

        try {
            return (List<Film>) q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
