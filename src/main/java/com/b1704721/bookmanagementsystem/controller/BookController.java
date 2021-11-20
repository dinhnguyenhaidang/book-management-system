package com.b1704721.bookmanagementsystem.controller;

import com.b1704721.bookmanagementsystem.dto.BookDTO;
import com.b1704721.bookmanagementsystem.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to book
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping(value = "{bookId}")
    public ResponseEntity<BookDTO> getRecordById(@PathVariable(value = "bookId") Long bookId) {
        BookDTO responseDTO = bookService.getRecordById(bookId);
        if (responseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createRecord(@RequestBody BookDTO bookDTO) {
        BookDTO responseDTO = bookService.saveRecord(bookDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookDTO> updateRecord(@RequestBody BookDTO bookDTO) {
        BookDTO responseDTO = bookService.updateRecord(bookDTO);
        if (responseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "{bookId}")
    public ResponseEntity<BookDTO> deleteRecordById(@PathVariable(value = "bookId") Long bookId) {
        BookDTO responseDTO = bookService.getRecordById(bookId);
        if (responseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteRecordById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
