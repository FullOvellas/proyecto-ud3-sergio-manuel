package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "vault_id")
    private List<FilmVault> filmVaults;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "vault_id")
    private List<BookVault> bookVaults;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FilmVault> getFilmVaults() {
        return filmVaults;
    }

    public void setFilmVaults(List<FilmVault> filmVaults) {
        this.filmVaults = filmVaults;
    }

    public List<BookVault> getBookVaults() {
        return bookVaults;
    }

    public void setBookVaults(List<BookVault> bookVaults) {
        this.bookVaults = bookVaults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
