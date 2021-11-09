package com.b1704721.bookmanager.controller;

import com.b1704721.bookmanager.dto.AuthorDTO;
import com.b1704721.bookmanager.dto.BookDTO;
import com.b1704721.bookmanager.service.IAuthorService;
import com.b1704721.bookmanager.service.IBookService;
import org.junit.*;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Provides smoke tests
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
public class SmokeTest extends AbstractControllerTest {

    @MockBean
    private IAuthorService authorService;

    @MockBean
    private IBookService bookService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of SmokeTest.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of SmokeTest.");
    }

    @Before
    public void setUp() {
        System.out.println("Setting up.");
        super.setUp();
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down.");
    }

    @Test
    public void testCreateBook_success() throws Exception {
        System.out.println("Testing createBook_success.");

        // Given
        BookDTO inputBookDTO = new BookDTO();
        inputBookDTO.setTitle("Book Title 1");
        String inputJson = super.mapToJson(inputBookDTO);

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Book Title 1");

        Mockito.when(bookService.saveRecord(Mockito.any())).thenReturn(expectedBookDTO);

        // When
        String uri = "/books";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(201, response.getStatus());

        Mockito.verify(bookService, Mockito.times(1)).saveRecord(Mockito.any());

        BookDTO actualBookDTO = super.mapFromJson(response.getContentAsString(), BookDTO.class);
        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
    }

    @Test
    public void testCreateAuthor_success() throws Exception {
        System.out.println("Testing createAuthor_success.");

        // Given
        AuthorDTO inputAuthorDTO = new AuthorDTO();
        inputAuthorDTO.setName("Author Name 1");
        String inputJson = super.mapToJson(inputAuthorDTO);

        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Author Name 1");

        Mockito.when(authorService.saveRecord(Mockito.any())).thenReturn(expectedAuthorDTO);

        // When
        String uri = "/authors";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(201, response.getStatus());

        Mockito.verify(authorService, Mockito.times(1)).saveRecord(Mockito.any());

        AuthorDTO actualAuthorDTO = super.mapFromJson(response.getContentAsString(), AuthorDTO.class);
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
    }

}
