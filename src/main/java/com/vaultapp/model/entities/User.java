package com.vaultapp.model.entities;

import com.vaultapp.utilities.Cipher;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    @Column(name = "last_connection")
    private LocalDateTime lastConnection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<FilmVault> filmVaults;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<BookVault> bookVaults;

    public User() {
        filmVaults = new ArrayList<>();
        bookVaults = new ArrayList<>();
    }


    public User(String name, String password) {
        this();
        this.name = name;
        this.password = Cipher.getInstance().encrypt(password);
    }

    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(LocalDateTime lastConnection) {
        this.lastConnection = lastConnection;
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
     *
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

    public void removeVault(Vault vault) {
        if (vault instanceof BookVault) {
            bookVaults.remove((BookVault) vault);
        } else if (vault instanceof FilmVault) {
            filmVaults.remove((FilmVault) vault);
        }
    }

    public List<Vault> findVaultByName(String title) {
        List<Vault> vaults = new LinkedList<>();
        List<BookVault> bv = bookVaults.stream().filter(v -> v.getName().equals(title)).collect(Collectors.toList());
        List<FilmVault> fv = filmVaults.stream().filter(v -> v.getName().equals(title)).collect(Collectors.toList());
        vaults.addAll(bv);
        vaults.addAll(fv);
        return vaults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(Cipher.getInstance().decrypt(password),
                Cipher.getInstance().decrypt(user.password));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Cipher.getInstance().decrypt(password));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
