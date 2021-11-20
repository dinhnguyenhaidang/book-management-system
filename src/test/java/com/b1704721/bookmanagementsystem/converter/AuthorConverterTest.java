package com.b1704721.bookmanagementsystem.converter;

import com.b1704721.bookmanagementsystem.dto.AuthorDTO;
import com.b1704721.bookmanagementsystem.entity.AuthorEntity;
import com.b1704721.bookmanagementsystem.entity.BookEntity;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests AuthorConverter
 *
 * @author B1704721
 * @version 1.1
 * @since 20-Nov-2021
 */
public class AuthorConverterTest {

    AuthorConverter authorConverter;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of AuthorConverterTest.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of AuthorConverterTest.");
    }

    @Before
    public void setUp() {
        System.out.println("Setting up.");
        authorConverter = new AuthorConverter();
    }

    @After
    public void tearDown() {
        authorConverter = null;
        System.out.println("Tearing down.");
    }

    /**
     * TC_AC01_01
     */
    @Test
    public void testToDTO_success() {
        System.out.println("Testing toDTO_success.");

        // Given
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1L);
        authorEntity.setName("Author Name 1");
        List<BookEntity> bookEntities = new ArrayList<>();
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setId(1L);
        BookEntity bookEntity2 = new BookEntity();
        bookEntity2.setId(2L);
        bookEntities.add(bookEntity1);
        bookEntities.add(bookEntity2);
        authorEntity.setBooks(bookEntities);

        // When
        AuthorDTO authorDTO = authorConverter.toDTO(authorEntity);

        // Then
        Assert.assertEquals(authorEntity.getId(), authorDTO.getId());
        Assert.assertEquals(authorEntity.getName(), authorDTO.getName());
        if (authorEntity.getBooks() != null) {
            Assert.assertEquals(authorEntity.getBooks().size(), authorDTO.getBookIds().size());
            for (int i = 0; i < authorEntity.getBooks().size(); i++) {
                Assert.assertEquals(authorEntity.getBooks().get(i).getId(), authorDTO.getBookIds().get(i));
            }
        }
    }

    /**
     * TC_AC02_01
     */
    @Test
    public void testToEntity_success() {
        System.out.println("Testing toEntity_success.");

        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Author Name 1");

        // When
        AuthorEntity authorEntity = authorConverter.toEntity(authorDTO);

        // Then
        Assert.assertEquals(authorDTO.getId(), authorEntity.getId());
        Assert.assertEquals(authorDTO.getName(), authorEntity.getName());
    }

}
