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
    private List<Film> pageFilms;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_results")
    private int totalResults;
}
