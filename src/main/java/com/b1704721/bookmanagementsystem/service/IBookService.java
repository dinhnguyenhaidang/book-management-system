package com.b1704721.bookmanagementsystem.service;

import com.b1704721.bookmanagementsystem.dto.BookDTO;

/**
 * Provides services related to book and follows Dependency Injection design pattern
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
public interface IBookService {

    BookDTO saveRecord(BookDTO bookDTO);

    BookDTO getRecordById(long bookId);

    BookDTO updateRecord(BookDTO bookDTO);

    void deleteRecordById(long bookId);

}
