package com.b1704721.bookmanagementsystem.dto;

import java.util.List;

/**
 * Creates data transfer objects for books
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
public class BookDTO extends AbstractDTO {

    private String title;
    private List<Long> authorIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Long> authorIds) {
        this.authorIds = authorIds;
    }

}
