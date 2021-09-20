package com.b1704721.bookmanager.dto;

/**
 * Provides a base for other DTOs
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 * @param <T>
 */
public abstract class AbstractDTO<T> {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
