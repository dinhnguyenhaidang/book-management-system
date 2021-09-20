package com.b1704721.bookmanager.repository;

import com.b1704721.bookmanager.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides supported queries
 *
 * @author B1704721
 * @version 1.0
 * @since 15-Sep-2021
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
