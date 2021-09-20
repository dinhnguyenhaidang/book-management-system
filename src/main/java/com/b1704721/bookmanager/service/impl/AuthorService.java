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
 * Provides services related to author
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
@Service
public class AuthorService implements IAuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorConverter authorConverter;

    @Override
    public List<AuthorDTO> getAllRecords() {
        // Get all entities
        List<AuthorEntity> allEntities = authorRepository.findAll();
        List<AuthorDTO> allDTOs = new ArrayList<>();

        // Convert entities to DTOs
        for (AuthorEntity entity : allEntities) {
            allDTOs.add(authorConverter.toDTO(entity));
        }

        return allDTOs;
    }

    @Override
    public AuthorDTO getRecordById(long authorId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId).get();
        return authorConverter.toDTO(authorEntity);
    }

    @Override
    public AuthorDTO createRecord(AuthorDTO authorDTO) {
        // Convert authorDTO to an entity and assign it to authorEntity
        AuthorEntity authorEntity = authorConverter.toEntity(authorDTO);

        // Get books having provided ids
        try {
            List<BookEntity> bookEntities = bookRepository.findAllById(authorDTO.getBookIds());
            authorEntity.setBooks(bookEntities);
        } catch (Exception ex) {
            authorEntity.setBooks(null);
        }

        // Save the entity
        authorEntity = authorRepository.save(authorEntity);

        return authorConverter.toDTO(authorEntity);
    }

    @Override
    public AuthorDTO updateRecord(AuthorDTO authorDTO) {
        // Get old entity
        AuthorEntity authorEntity = authorRepository.findById(authorDTO.getId()).get();

        // Remove all old books
        for (BookEntity bookEntity : authorEntity.getBooks()) {
            authorEntity.removeFrom(bookEntity);
            bookRepository.save(bookEntity);
        }
        authorEntity.setBooks(new ArrayList<>());

        // Convert authorDTO to an entity and update it
        authorEntity = authorConverter.toEntity(authorDTO);

        // Get books having provided ids
        try {
            List<BookEntity> bookEntities = bookRepository.findAllById(authorDTO.getBookIds());
            // Add new books
            for (BookEntity songEntity : bookEntities) {
                authorEntity.addTo(songEntity);
                bookRepository.save(songEntity);
            }
        } catch (Exception ex) {
            authorEntity.setBooks(null);
        }

        // Save the entity
        authorEntity = authorRepository.save(authorEntity);

        return authorConverter.toDTO(authorEntity);
    }

    @Override
    public void deleteRecordById(long authorId) throws Exception {
        if (!authorRepository.findById(authorId).isPresent()) {
            throw new Exception("Author with id " + authorId + " does not exist.");
        }

        bookRepository.deleteById(authorId);
    }

}
