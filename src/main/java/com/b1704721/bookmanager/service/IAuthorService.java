package com.b1704721.bookmanager.service;

import com.b1704721.bookmanager.dto.AuthorDTO;

import java.util.List;

/**
 * Follows template for Data Injection
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
public interface IAuthorService {

    List<AuthorDTO> getAllRecords();

    AuthorDTO getRecordById(long authorId);

    AuthorDTO createRecord(AuthorDTO authorDTO);

    AuthorDTO updateRecord(AuthorDTO authorDTO);

    void deleteRecordById(long authorId) throws Exception;

}
