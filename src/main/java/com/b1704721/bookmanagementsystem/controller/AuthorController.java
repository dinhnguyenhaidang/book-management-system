package com.b1704721.bookmanagementsystem.controller;

import com.b1704721.bookmanagementsystem.dto.AuthorDTO;
import com.b1704721.bookmanagementsystem.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests related to author
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @GetMapping(value = "{authorId}")
    public ResponseEntity<AuthorDTO> getRecordById(@PathVariable(value = "authorId") Long authorId) {
        AuthorDTO responseDTO = authorService.getRecordById(authorId);
        if (responseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createRecord(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO responseDTO = authorService.saveRecord(authorDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AuthorDTO> updateRecord(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO responseDTO = authorService.updateRecord(authorDTO);
        if (responseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "{authorId}")
    public ResponseEntity<AuthorDTO> deleteRecordById(@PathVariable(value = "authorId") Long authorId) {
        AuthorDTO responseDTO = authorService.getRecordById(authorId);
        if (responseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteRecordById(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
