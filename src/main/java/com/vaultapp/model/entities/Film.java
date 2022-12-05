package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tmdbId;
    private String title;
    private String posterPath;
    @ElementCollection
    private List<String> genres;
    private String overview;
    private String originalTitle;
    @ElementCollection
    private List<String> productionCompany;
    private LocalDate releaseDate;
    private String tagline;

    public Film(int tmdbId, String title, String posterPath, List<String> genres, String overview, String originalTitle, List<String> productionCompany, LocalDate releaseDate, String tagline) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.posterPath = posterPath;
        this.genres = genres;
        this.overview = overview;
        this.originalTitle = originalTitle;
        this.productionCompany = productionCompany;
        this.releaseDate = releaseDate;
        this.tagline = tagline;
    }

    public Film() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
