package com.vaultapp.model.dao;

import com.vaultapp.model.entities.Film;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FilmDao implements Dao<Film>{
    private EntityManager em;

    public FilmDao(EntityManager entityManager) {
        em = entityManager;
    }

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
