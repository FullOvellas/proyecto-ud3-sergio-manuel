package com.vaultapp.model.pojo.film.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaultapp.model.entities.Film;
import com.vaultapp.model.pojo.film.ApiFilm;
import com.vaultapp.model.pojo.film.FilmResultPage;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FilmApiDao {

    private final String SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s&page=%d";
    private final String MOVIE_URL = "https://api.themoviedb.org/3/movie/%d?api_key=%s";
    private final String POSTER_URL = "https://image.tmdb.org/t/p/original/%s";
    private final String API_KEY = "19ccdf01a305d5f5c3485958c90ef5d6";

    private static FilmApiDao instance;

    static {
        instance = new FilmApiDao();
    }

    public static FilmApiDao getInstance() { return instance; }

    public List<Film> searchByTitle(String title, int page) {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = String.format(SEARCH_URL, API_KEY ,title, page);
        List<Film> films = new ArrayList<>();

        try {
            FilmResultPage resultPage = objectMapper.readValue(new URL(URLEncoder.encode(url, StandardCharsets.UTF_8)), FilmResultPage.class);
            List<ApiFilm> pageFilms = resultPage.getPageFilms();
            for (ApiFilm a : pageFilms) {
                films.add(parseApiFilm(a));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return films;
    }

    private Film parseApiFilm(ApiFilm apiFilm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        int tmdbId = apiFilm.getId();
        String title = apiFilm.getTitle();
        String posterPath = apiFilm.getPosterPath();
        List<String> genres = new ArrayList<>();
        apiFilm.getGenres().forEach(genresItem -> genres.add(genresItem.getName()));
        String overview = apiFilm.getOverview();
        String originalTitle = apiFilm.getOriginalTitle();
        List<String> productionCompanies = new ArrayList<>();
        apiFilm.getProductionCompanies().forEach(productionCompany -> productionCompanies.add(productionCompany.getName()));
        LocalDate releaseDate = LocalDate.parse(apiFilm.getReleaseDate(), formatter);
        String tagline = apiFilm.getTagline();

        return new Film(tmdbId,
                title,
                posterPath,
                genres,
                overview,
                originalTitle,
                productionCompanies,
                releaseDate,
                tagline);
    }

    public Film searchBytmdbId(int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        ApiFilm film = null;
        Film out;
        String url = String.format(MOVIE_URL,id, API_KEY);

        try {
            film = objectMapper.readValue(new URL(URLEncoder.encode(url, StandardCharsets.UTF_8)), ApiFilm.class);
            out = parseApiFilm(film);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return out;
    }

}
