package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.User;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    private EntityManager em;

    private static UserDao instance;

    static { instance = new UserDao(JpaUtil.getEntityManager()); }

    private UserDao(EntityManager entityManager) {
        em = entityManager;
    }

    public static UserDao getInstance() { return instance; }

    @Override
    public void create(User user) {
        if (user.getId() != null && user.getId() > 0) {
            em.merge(user);
        } else {
            em.persist(user);
        }
    }

    @Override
    public List<User> read() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        if (em.find(User.class, id) != null) {
            em.remove(em.find(User.class, id));
        }
    }

    public User find(String name) {
        Query q = em.createQuery("select u from User u where name = :name");
        q.setParameter("name", name);
        try {
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
