package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "vault_id")
    private List<FilmVault> filmVaults;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "vault_id")
    private List<BookVault> bookVaults;

    public User() {
        filmVaults = new ArrayList<>();
        bookVaults = new ArrayList<>();
    }


    public User(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    /**
     * Adds a vault for the user. Symmetrically defines an owner for the vault.
     * This method ensures the bidirectional class relation between <code>User</code> and
     * <code>Vault</code>.
     * @param vault
     */
    public void addVault(Vault vault) {
        if (vault instanceof FilmVault) {
            filmVaults.add((FilmVault) vault);
            ((FilmVault) vault).setOwner(this);
        } else if (vault instanceof BookVault) {
            bookVaults.add((BookVault) vault);
            ((BookVault) vault).setOwner(this);
        }
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
