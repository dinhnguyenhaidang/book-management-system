package com.b1704721.bookmanager.controller;

import com.b1704721.bookmanager.dto.BookDTO;
import com.b1704721.bookmanager.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles requests related to book
 *
 * @author B1704721
 * @version 1.0
 * @since 16-Sep-2021
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<BookDTO> getAllRecords() {
        return bookService.getAllRecords();
    }

    @GetMapping(value = "{bookId}")
    public BookDTO getRecordById(@PathVariable(value = "bookId") Long bookId) {
        return bookService.getRecordById(bookId);
    }

    @PostMapping
    public BookDTO createRecord(@RequestBody BookDTO bookDTO) {
        return bookService.createRecord(bookDTO);
    }

    @PutMapping
    public BookDTO updateRecord(@RequestBody BookDTO bookDTO) {
        return bookService.updateRecord(bookDTO);
    }

    @DeleteMapping(value = "{bookId}")
    public void deleteRecordById(@PathVariable(value = "bookId") Long bookId) throws Exception {
        bookService.deleteRecordById(bookId);
    }

}
