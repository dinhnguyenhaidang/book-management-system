package com.b1704721.bookmanager.service.impl;

import com.b1704721.bookmanager.converter.BookConverter;
import com.b1704721.bookmanager.dto.BookDTO;
import com.b1704721.bookmanager.entity.BookEntity;
import com.b1704721.bookmanager.repository.AuthorRepository;
import com.b1704721.bookmanager.repository.BookRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

/**
 * Tests BookService
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private BookConverter bookConverter;
    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of BookServiceTest.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of BookServiceTest.");
    }

    @Before
    public void setUp() {
        System.out.println("Setting up.");

        bookConverter = Mockito.mock(BookConverter.class);
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        bookRepository = Mockito.mock(BookRepository.class);

        bookService = new BookService();
        bookService.setBookConverter(bookConverter);
        bookService.setAuthorRepository(authorRepository);
        bookService.setBookRepository(bookRepository);
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down.");
    }

    @Test
    public void testGetRecordById_success() {
        System.out.println("Running testGetRecordById_success.");

        // Given
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setTitle("Book Title 1");
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Book Title 1");

        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(optionalBookEntity);
        Mockito.when(bookConverter.toDTO(Mockito.any())).thenReturn(expectedBookDTO);

        // When
        BookDTO actualBookDTO = bookService.getRecordById(bookEntity.getId());

        // Then
        Mockito.verify(bookRepository, Mockito.times(2)).findById(Mockito.anyLong());

        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
    }

    @Test
    public void testSaveRecord_success() {
        System.out.println("Running testSaveRecord_success.");

        // Given
        BookEntity BookEntity = new BookEntity();
        BookEntity.setId(1L);
        BookEntity.setTitle("Book Title 1");

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Book Title 1");

        Mockito.when(bookConverter.toEntity(Mockito.any())).thenReturn(BookEntity);
        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(BookEntity);
        Mockito.when(bookConverter.toDTO(Mockito.any())).thenReturn(expectedBookDTO);

        // When
        BookDTO actualBookDTO = bookService.saveRecord(expectedBookDTO);

        // Then
        Mockito.verify(bookRepository, Mockito.times(1)).save(BookEntity);
        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
    }

    @Test
    public void testUpdateRecord_success() {
        System.out.println("Running testUpdateRecord_success.");

        // Given
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setTitle("Book Title 1");
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Book Title 1");

        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(optionalBookEntity);
        Mockito.when(bookConverter.toEntity(Mockito.any())).thenReturn(bookEntity);
        Mockito.when(bookRepository.save(Mockito.any(BookEntity.class))).thenReturn(bookEntity);
        Mockito.when(bookConverter.toDTO(Mockito.any())).thenReturn(expectedBookDTO);

        // When
        BookDTO actualBookDTO = bookService.updateRecord(expectedBookDTO);

        // Then
        Mockito.verify(bookRepository, Mockito.times(1)).save(bookEntity);
        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
    }
    
    @Test
    public void testDeleteRecordById_success() {
        System.out.println("Running testDeleteRecordById_success.");

        // Given
        long bookId = 1L;
        Mockito.doNothing().when(bookRepository).deleteById(bookId);

        // When
        bookService.deleteRecordById(bookId);

        // Then
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookId);
    }

}
