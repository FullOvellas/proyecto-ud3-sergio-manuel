package com.vaultapp.model.entities.dao;

import com.vaultapp.model.entities.Book;
import com.vaultapp.model.entities.User;
import com.vaultapp.utilities.JpaUtil;
import com.vaultapp.utilities.filters.BookFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class BookDao implements Dao<Book> {
    private EntityManager em;

    private static BookDao instance;

    static {
        instance = new BookDao(JpaUtil.getEntityManager());
    }

    private BookDao(EntityManager entityManager) {
        this.em = entityManager;
    }

    public static BookDao getInstance() {
        return instance;
    }

    @Override
    public void create(Book book) {
        if (book.getId() != null && book.getId() > 0) {
            em.merge(book);
        } else {
            em.persist(book);
        }
    }

    @Override
    public List<Book> read() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        if (em.find(Book.class, id) != null) {
            em.remove(em.find(Book.class, id));
        }
    }

    public List<Book> findBy(User user, BookFilter filter, String arg) {
        Query q;

        if (filter == BookFilter.TITLE) {
            q = em.createQuery("select b from Book b join User u where u = :user and b.title = :title");
            q.setParameter("title", arg);
            q.setParameter("user", user);
        } else if (filter == BookFilter.AUTHOR) {
            q = em.createQuery("select b from Book b join User u where u = :user and  b.author = :author");
            q.setParameter("author", arg);
            q.setParameter("user", user);
        } else if (filter == BookFilter.ISBN) {
            q = em.createQuery("select b from Book b join User u where u = :user and  b.isbn = :isbn");
            q.setParameter("isbn", arg);
            q.setParameter("user", user);
        } else {
            return null;
        }

        try {
            return (List<Book>) q.getResultList();

        } catch (Exception e) {
            return null;
        }
    }
}
