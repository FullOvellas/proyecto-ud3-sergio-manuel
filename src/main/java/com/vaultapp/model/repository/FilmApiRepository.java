package com.vaultapp.model.repository;


import com.vaultapp.model.entities.Film;
import com.vaultapp.model.pojo.film.dao.FilmApiDao;

import java.util.List;

public class FilmApiRepository {
    private static FilmApiRepository instance;

    static {
        instance = new FilmApiRepository();
    }

    public static FilmApiRepository getInstance() {
        return instance;
    }

    /**
     * Gets the list of films in relation of the provided title.
     * @param title of the book.
     * @return list of books.
     */
    public List<Film> getAsListByTitle(String title) {
        return FilmApiDao.getInstance().searchByTitle(title, 1);
    }


    /**
     * Gets the list of films in relation of the provided tmid.
     * @param tmid of the book.
     * @return list of books.
     */
    public Film getByIsbn(String tmid) {
        return null;
    }
}
