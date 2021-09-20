package com.b1704721.bookmanager.controller;

import com.b1704721.bookmanager.dto.AuthorDTO;
import com.b1704721.bookmanager.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles requests related to author
 *
 * @author B1704721
 * @version 1.0
 * @since 16-Sep-2021
 */
@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @GetMapping
    public List<AuthorDTO> getAllRecords() {
        return authorService.getAllRecords();
    }

    @GetMapping(value = "{authorId}")
    public AuthorDTO getRecordById(@PathVariable(value = "authorId") Long authorId) {
        return authorService.getRecordById(authorId);
    }

    @PostMapping
    public AuthorDTO createRecord(@RequestBody AuthorDTO authorDTO) {
        return authorService.createRecord(authorDTO);
    }

    @PutMapping
    public AuthorDTO updateRecord(@RequestBody AuthorDTO authorDTO) {
        return authorService.updateRecord(authorDTO);
    }

    @DeleteMapping(value = "{authorId}")
    public void deleteRecordById(@PathVariable(value = "authorId") Long authorId) throws Exception {
        authorService.deleteRecordById(authorId);
    }

}
