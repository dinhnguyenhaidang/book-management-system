package com.b1704721.bookmanager.dto;

import java.util.List;

/**
 * Creates data transfer objects for books
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
public class AuthorDTO extends AbstractDTO {

    private String name;
    private List<Long> bookIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }

}
