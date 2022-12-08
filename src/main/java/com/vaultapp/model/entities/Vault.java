package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vault<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void addElement(T t){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vault)) return false;
        Vault vault = (Vault) o;
        return Objects.equals(id, vault.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
