package com.b1704721.bookmanager.service.impl;

import com.b1704721.bookmanager.converter.BookConverter;
import com.b1704721.bookmanager.dto.BookDTO;
import com.b1704721.bookmanager.entity.AuthorEntity;
import com.b1704721.bookmanager.entity.BookEntity;
import com.b1704721.bookmanager.repository.AuthorRepository;
import com.b1704721.bookmanager.repository.BookRepository;
import com.b1704721.bookmanager.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides services related to book
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
@Service
public class BookService implements IBookService {

    @Autowired
    BookConverter bookConverter;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public void setBookConverter(BookConverter bookConverter) {
        this.bookConverter = bookConverter;
    }

    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO getRecordById(long bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).get();
        return bookConverter.toDTO(bookEntity);
    }

    @Override
    public BookDTO saveRecord(BookDTO bookDTO) {
        // Convert bookDTO to an entity and assign it to bookEntity
        BookEntity bookEntity = bookConverter.toEntity(bookDTO);

        // Get authors having provided ids
        try {
            List<AuthorEntity> authorEntities = authorRepository.findAllById(bookDTO.getAuthorIds());
            bookEntity.setAuthors(authorEntities);
        } catch (Exception ex) {
            bookEntity.setAuthors(new ArrayList<>());
        }

        // Save the entity
        bookEntity = bookRepository.save(bookEntity);

        return bookConverter.toDTO(bookEntity);
    }

    @Override
    public BookDTO updateRecord(BookDTO bookDTO) {
        // Get old entity
        BookEntity bookEntity = bookRepository.findById(bookDTO.getId()).get();

        // Convert bookDTO to an entity and update it
        bookEntity = bookConverter.toEntity(bookDTO);

        // Get authors having provided ids
        try {
            List<AuthorEntity> authorEntities = authorRepository.findAllById(bookDTO.getAuthorIds());
            bookEntity.setAuthors(authorEntities);
        } catch (Exception ex) {
            bookEntity.setAuthors(new ArrayList<>());
        }

        // Save the entity
        bookEntity = bookRepository.save(bookEntity);

        return bookConverter.toDTO(bookEntity);
    }

    @Override
    public void deleteRecordById(long bookId) throws Exception {
        if (!bookRepository.findById(bookId).isPresent()) {
            throw new Exception("Book with id " + bookId + " does not exist.");
        }

        bookRepository.deleteById(bookId);
    }

}
