package com.b1704721.bookmanagementsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines author entity, its relationships and maps it to the corresponding table in database
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
@Entity
@Table(name = "author")
public class AuthorEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "authors")
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

    public void addTo(BookEntity bookEntity) {
        this.books.add(bookEntity);
        bookEntity.getAuthors().add(this);
    }

    public void removeFrom(BookEntity bookEntity) {
        bookEntity.getAuthors().remove(this);
    }

}
