package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.Film;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

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
}
