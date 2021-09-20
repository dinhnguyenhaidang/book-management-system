package com.b1704721.bookmanager.converter;

import com.b1704721.bookmanager.dto.BookDTO;
import com.b1704721.bookmanager.entity.AuthorEntity;
import com.b1704721.bookmanager.entity.BookEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts DTO to entity and vice versa
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
@Component
public class BookConverter {

    public BookDTO toDTO(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();

        // Get author ids from book's author set
        List<Long> authorIds = new ArrayList<>();
        try {
            for (AuthorEntity author : bookEntity.getAuthors()) {
                authorIds.add(author.getId());
            }
        } catch (NullPointerException ex) {
            authorIds = null;
        }

        // Set values from the entity to the DTO
        if (bookEntity.getId() != null) {
            bookDTO.setId(bookEntity.getId());
        }
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setAuthorIds(authorIds);

        return bookDTO;
    }

    public BookEntity toEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();

        // Set values from the DTO to the entity
        if (bookDTO.getId() != null) {
            bookEntity.setId(bookDTO.getId());
        }
        bookEntity.setTitle(bookDTO.getTitle());

        return bookEntity;
    }

}
