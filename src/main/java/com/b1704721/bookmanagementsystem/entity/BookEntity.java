package com.b1704721.bookmanagementsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines book entity, its relationships and maps it to the corresponding table in database
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
@Entity
@Table(name = "book")
public class BookEntity extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<AuthorEntity> authors = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

}
