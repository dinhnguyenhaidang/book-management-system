package com.b1704721.bookmanagementsystem.service.impl;

import com.b1704721.bookmanagementsystem.converter.AuthorConverter;
import com.b1704721.bookmanagementsystem.dto.AuthorDTO;
import com.b1704721.bookmanagementsystem.entity.AuthorEntity;
import com.b1704721.bookmanagementsystem.repository.AuthorRepository;
import com.b1704721.bookmanagementsystem.repository.BookRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

/**
 * Tests AuthorService
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    private AuthorConverter authorConverter;
    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of AuthorServiceTest.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of AuthorServiceTest.");
    }

    @Before
    public void setUp() {
        System.out.println("Setting up.");

        authorConverter = Mockito.mock(AuthorConverter.class);
        authorRepository = Mockito.mock(AuthorRepository.class);
        BookRepository bookRepository = Mockito.mock(BookRepository.class);

        authorService = new AuthorService();
        authorService.setAuthorConverter(authorConverter);
        authorService.setAuthorRepository(authorRepository);
        authorService.setBookRepository(bookRepository);
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down.");
    }

    /**
     * TC_AS01_01
     */
    @Test
    public void testSaveRecord_success() {
        System.out.println("Running testSaveRecord_success.");

        // Given
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1L);
        authorEntity.setName("Author Name 1");

        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Author Name 1");

        Mockito.when(authorConverter.toEntity(Mockito.any())).thenReturn(authorEntity);
        Mockito.when(authorRepository.save(Mockito.any(AuthorEntity.class))).thenReturn(authorEntity);
        Mockito.when(authorConverter.toDTO(Mockito.any())).thenReturn(expectedAuthorDTO);

        // When
        AuthorDTO actualAuthorDTO = authorService.saveRecord(expectedAuthorDTO);

        // Then
        Mockito.verify(authorRepository, Mockito.times(1)).save(authorEntity);
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
    }

    /**
     * TC_AS02_01
     */
    @Test
    public void testGetRecordById_success() {
        System.out.println("Running testGetRecordById_success.");

        // Given
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1L);
        authorEntity.setName("Author Name 1");
        Optional<AuthorEntity> optionalAuthorEntity = Optional.of(authorEntity);

        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Author Name 1");

        Mockito.when(authorRepository.findById(Mockito.anyLong())).thenReturn(optionalAuthorEntity);
        Mockito.when(authorConverter.toDTO(Mockito.any())).thenReturn(expectedAuthorDTO);

        // When
        AuthorDTO actualAuthorDTO = authorService.getRecordById(authorEntity.getId());

        // Then
        Mockito.verify(authorRepository, Mockito.times(2)).findById(Mockito.anyLong());

        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
    }

    /**
     * TC_AS02_02
     */
    @Test
    public void testGetRecordById_invalidId() {
        System.out.println("Running testGetRecordById_invalidId.");

        // Given
        long authorId = 0L;

        // When
        AuthorDTO actualAuthorDTO = authorService.getRecordById(authorId);

        // Then
        Mockito.verify(authorRepository, Mockito.times(1)).findById(Mockito.anyLong());

        Assert.assertNull(actualAuthorDTO);
    }

    /**
     * TC_AS03_01
     */
    @Test
    public void testUpdateRecord_success() {
        System.out.println("Running testUpdateRecord_success.");

        // Given
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1L);
        authorEntity.setName("Updated Author Name 1");
        Optional<AuthorEntity> optionalAuthorEntity = Optional.of(authorEntity);

        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Updated Author Name 1");

        Mockito.when(authorRepository.findById(Mockito.anyLong())).thenReturn(optionalAuthorEntity);
        Mockito.when(authorConverter.toEntity(Mockito.any())).thenReturn(authorEntity);
        Mockito.when(authorRepository.save(Mockito.any(AuthorEntity.class))).thenReturn(authorEntity);
        Mockito.when(authorConverter.toDTO(Mockito.any())).thenReturn(expectedAuthorDTO);

        // When
        AuthorDTO actualAuthorDTO = authorService.updateRecord(expectedAuthorDTO);

        // Then
        Mockito.verify(authorRepository, Mockito.times(1)).save(Mockito.any());
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
    }

    /**
     * TC_AS03_02
     */
    @Test
    public void testUpdateRecord_invalidId() {
        System.out.println("Running testUpdateRecord_invalidId.");

        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(0L);
        authorDTO.setName("Updated Author Name 0");

        // When
        AuthorDTO actualAuthorDTO = authorService.updateRecord(authorDTO);

        // Then
        Mockito.verify(authorRepository, Mockito.times(0)).save(Mockito.any());
        Assert.assertNull(actualAuthorDTO);
    }

    /**
     * TC_AS04_01
     */
    @Test
    public void testDeleteRecordById_success() {
        System.out.println("Running testDeleteRecordById_success.");

        // Given
        long authorId = 1L;
        Mockito.doNothing().when(authorRepository).deleteById(authorId);

        // When
        authorService.deleteRecordById(authorId);

        // Then
        Mockito.verify(authorRepository, Mockito.times(1)).deleteById(authorId);
    }

}
