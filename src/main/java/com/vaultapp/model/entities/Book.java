package com.vaultapp.model.entities;

import jakarta.persistence.*;

import java.net.URL;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book extends VaultItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String publishYear;

    @Column(unique = true)
    private String isbn;

    private URL cover;

    private boolean status;


    public Book() {
    }

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public Book(String title, String author, String publishYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.isbn = isbn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public URL getCover() {
        return cover;
    }

    public void setCover(URL cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void changeStatus() {
        status = !status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", isbn='" + isbn + '\'' +
                ", cover='" + cover + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
