package com.b1704721.bookmanagementsystem.converter;

import com.b1704721.bookmanagementsystem.dto.BookDTO;
import com.b1704721.bookmanagementsystem.entity.AuthorEntity;
import com.b1704721.bookmanagementsystem.entity.BookEntity;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests BookConverter
 *
 * @author B1704721
 * @version 1.1
 * @since 20-Nov-2021
 */
public class BookConverterTest {

    BookConverter bookConverter;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of BookConverterTest.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of BookConverterTest.");
    }

    @Before
    public void setUp() {
        System.out.println("Setting up.");
        bookConverter = new BookConverter();
    }

    @After
    public void tearDown() {
        bookConverter = null;
        System.out.println("Tearing down.");
    }

    /**
     * TC_BC01_01
     */
    @Test
    public void testToDTO_success() {
        System.out.println("Testing toDTO_success.");

        // Given
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setTitle("Book 1");
        List<AuthorEntity> authorEntities = new ArrayList<>();
        AuthorEntity authorEntity1 = new AuthorEntity();
        authorEntity1.setId(1L);
        AuthorEntity authorEntity2 = new AuthorEntity();
        authorEntity2.setId(2L);
        authorEntities.add(authorEntity1);
        authorEntities.add(authorEntity2);
        bookEntity.setAuthors(authorEntities);

        // When
        BookDTO bookDTO = bookConverter.toDTO(bookEntity);

        // Then
        Assert.assertEquals(bookEntity.getId(), bookDTO.getId());
        Assert.assertEquals(bookEntity.getTitle(), bookDTO.getTitle());
        if (bookEntity.getAuthors() != null) {
            Assert.assertEquals(bookEntity.getAuthors().size(), bookDTO.getAuthorIds().size());
            for (int i = 0; i < bookEntity.getAuthors().size(); i++) {
                Assert.assertEquals(bookEntity.getAuthors().get(i).getId(), bookDTO.getAuthorIds().get(i));
            }
        }
    }

    /**
     * TC_BC02_01
     */
    @Test
    public void testToEntity_success() {
        System.out.println("Testing toEntity_success.");

        // Given
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Book 1");

        // When
        BookEntity bookEntity = bookConverter.toEntity(bookDTO);

        // Then
        Assert.assertEquals(bookDTO.getId(), bookEntity.getId());
        Assert.assertEquals(bookDTO.getTitle(), bookEntity.getTitle());
    }

}
