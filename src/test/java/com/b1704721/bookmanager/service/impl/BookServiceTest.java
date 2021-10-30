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

import java.util.Arrays;
import java.util.Optional;

/**
 * Tests BookService
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
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
        bookRepository = Mockito.mock(BookRepository.class);

        bookService = new BookService();
        bookService.setBookConverter(bookConverter);
        bookService.setBookRepository(bookRepository);
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down.");
    }

    @Test
    public void testGetRecordById() throws Exception {
        System.out.println("Running testGetRecordById.");

        // Given
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setTitle("Title");
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Title");

        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(optionalBookEntity);
        Mockito.when(bookConverter.toDTO(Mockito.any())).thenReturn(expectedBookDTO);

        // When
        BookDTO actualBookDTO = bookService.getRecordById(bookEntity.getId());

        // Then
        Mockito.verify(bookRepository, Mockito.times(1)).findById(Mockito.anyLong());

        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
    }

    @Test
    public void testSaveRecord() throws Exception {
        System.out.println("Running testSaveRecord.");

        // Given
        BookEntity BookEntity = new BookEntity();
        BookEntity.setId(1L);
        BookEntity.setTitle("Book Title");

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Book Title");

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
    public void testUpdateRecord() throws Exception {
        System.out.println("Running testUpdateRecord.");

        // Given
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        bookEntity.setTitle("Title");
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Title");

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
    public void testDelete() throws Exception {
        System.out.println("Running testDeleteRecordById.");

        // Given
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1L);
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);
        long bookId = bookEntity.getId();

        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(optionalBookEntity);

        // When
        bookService.deleteRecordById(bookId);

        // Then
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookEntity.getId());
    }

}