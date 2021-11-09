package com.b1704721.bookmanager.service.impl;

import com.b1704721.bookmanager.converter.AuthorConverter;
import com.b1704721.bookmanager.dto.AuthorDTO;
import com.b1704721.bookmanager.entity.AuthorEntity;
import com.b1704721.bookmanager.entity.BookEntity;
import com.b1704721.bookmanager.repository.AuthorRepository;
import com.b1704721.bookmanager.repository.BookRepository;
import com.b1704721.bookmanager.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements services related to author
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
@Service
public class AuthorService implements IAuthorService {

    @Autowired
    AuthorConverter authorConverter;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public void setAuthorConverter(AuthorConverter authorConverter) {
        this.authorConverter = authorConverter;
    }

    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorDTO saveRecord(AuthorDTO authorDTO) {
        // Convert authorDTO to authorEntity
        AuthorEntity authorEntity = authorConverter.toEntity(authorDTO);

        // Get books of this author
        try {
            List<BookEntity> bookEntities = bookRepository.findAllById(authorDTO.getBookIds());
            for (BookEntity bookEntity : bookEntities) {
                authorEntity.addTo(bookEntity);
            }
        } catch (Exception ex) {
            authorEntity.setBooks(new ArrayList<>());
        }

        // Save the entity
        authorEntity = authorRepository.save(authorEntity);

        return authorConverter.toDTO(authorEntity);
    }

    @Override
    public AuthorDTO getRecordById(long authorId) {
        // Validate id
        if (!authorRepository.findById(authorId).isPresent()) {
            return null;
        }

        AuthorEntity authorEntity = authorRepository.findById(authorId).get();
        return authorConverter.toDTO(authorEntity);
    }

    @Override
    public AuthorDTO updateRecord(AuthorDTO authorDTO) {
        // Validate id
        if (!authorRepository.findById(authorDTO.getId()).isPresent()) {
            return null;
        }

        // Get current authorEntity
        AuthorEntity authorEntity = authorRepository.findById(authorDTO.getId()).get();

        // Remove this author from this author's book's authors
        for (BookEntity bookEntity : authorEntity.getBooks()) {
            authorEntity.removeFrom(bookEntity);
            bookRepository.save(bookEntity);
        }
        authorEntity.setBooks(new ArrayList<>());

        // Convert authorDTO to authorEntity (update authorEntity)
        authorEntity = authorConverter.toEntity(authorDTO);

        // Add this author to this author's book's authors
        try {
            List<BookEntity> bookEntities = bookRepository.findAllById(authorDTO.getBookIds());
            for (BookEntity bookEntity : bookEntities) {
                authorEntity.addTo(bookEntity);
                bookRepository.save(bookEntity);
            }
        } catch (Exception ex) {
            authorEntity.setBooks(new ArrayList<>());
        }

        // Save the entity
        authorEntity = authorRepository.save(authorEntity);

        return authorConverter.toDTO(authorEntity);
    }

    @Override
    public void deleteRecordById(long authorId) {
        authorRepository.deleteById(authorId);
    }

}
