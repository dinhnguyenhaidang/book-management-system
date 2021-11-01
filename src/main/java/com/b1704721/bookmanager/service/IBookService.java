package com.b1704721.bookmanager.service;

import com.b1704721.bookmanager.dto.BookDTO;

/**
 * Provides services related to book and follows template for Data Injection
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
public interface IBookService {

    BookDTO getRecordById(long bookId);

    BookDTO saveRecord(BookDTO bookDTO);

    BookDTO updateRecord(BookDTO bookDTO);

    void deleteRecordById(long bookId);

}
