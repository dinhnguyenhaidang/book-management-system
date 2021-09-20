package com.b1704721.bookmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Defines author entity, its relationships and maps it to the corresponding table in database
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
@Entity
@Table(name = "author")
public class AuthorEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public void addBook(BookEntity bookEntity) {
        this.books.add(bookEntity);
        bookEntity.getAuthors().add(this);
    }

    public void removeFrom(BookEntity bookEntity) {
        bookEntity.getAuthors().remove(this);
    }

}
