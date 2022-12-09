package com.vaultapp.model.pojo.film;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * POJO matching the response for the /search/movie endpoint
 */
public class FilmResultPage {
    @JsonProperty("page")
    private int pageNumber;
    @JsonProperty("results")
    private List<ApiFilm> pageFilms;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_results")
    private int totalResults;

    public int getPageNumber() {
        return pageNumber;
    }

    public List<ApiFilm> getPageFilms() {
        return pageFilms;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
