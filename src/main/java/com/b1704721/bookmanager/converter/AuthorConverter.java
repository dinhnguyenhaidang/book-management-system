package com.b1704721.bookmanager.converter;

import com.b1704721.bookmanager.dto.AuthorDTO;
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
public class AuthorConverter {

    public AuthorDTO toDTO(AuthorEntity authorEntity) {
        AuthorDTO authorDTO = new AuthorDTO();

        // Get book ids from the author's book set
        List<Long> bookIds = new ArrayList<>();
        try {
            for (BookEntity song : authorEntity.getBooks()) {
                bookIds.add(song.getId());
            }
        } catch (NullPointerException ex) {
            bookIds = null;
        }

        // Set values from the entity to the DTO
        if (authorEntity.getId() != null) {
            authorDTO.setId(authorEntity.getId());
        }
        authorDTO.setName(authorEntity.getName());
        authorDTO.setBookIds(bookIds);

        return authorDTO;
    }

    public AuthorEntity toEntity(AuthorDTO authorDTO) {
        AuthorEntity authorEntity = new AuthorEntity();

        // Set values from the DTO to the entity
        if (authorDTO.getId() != null) {
            authorEntity.setId(authorDTO.getId());
        }
        authorEntity.setName(authorDTO.getName());

        return authorEntity;
    }

}
