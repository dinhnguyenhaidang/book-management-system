package com.b1704721.bookmanagementsystem.dto;

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

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String name, List<Long> bookIds) {
        this.setId(id);
        this.name = name;
        this.bookIds = bookIds;
    }

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
