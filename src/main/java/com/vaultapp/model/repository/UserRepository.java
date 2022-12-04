package com.vaultapp.model.repository;

import com.vaultapp.model.dao.Dao;
import com.vaultapp.model.dao.UserDao;
import com.vaultapp.model.entities.User;
import com.vaultapp.utilities.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRepository implements Repository<User> {
    private EntityManager em;
    private Dao<User> userDao;
    private static UserRepository instance;

    static { instance = new UserRepository(JpaUtil.getEntityManager()); }

    private UserRepository(EntityManager entityManager) {
        em = entityManager;
        userDao = UserDao.getInstance();
    }

    public static UserRepository getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        try {
            em.getTransaction().begin();
            userDao.create(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAsList() {
        return userDao.read();
    }

    @Override
    public void remove(Long id) {
        try {
            em.getTransaction().begin();
            userDao.delete(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
