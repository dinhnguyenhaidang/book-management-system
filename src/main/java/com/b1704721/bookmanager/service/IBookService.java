package com.b1704721.bookmanager.service;

import com.b1704721.bookmanager.dto.BookDTO;

import java.util.List;

/**
 * Follows template for Data Injection
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
public interface IBookService {

    List<BookDTO> getAllRecords();

    BookDTO getRecordById(long bookId);

    BookDTO createRecord(BookDTO bookDTO);

    BookDTO updateRecord(BookDTO bookDTO);

    void deleteRecordById(long bookId) throws Exception;

}
