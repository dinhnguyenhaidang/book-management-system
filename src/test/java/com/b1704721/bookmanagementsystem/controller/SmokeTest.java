package com.b1704721.bookmanagementsystem.controller;

import com.b1704721.bookmanagementsystem.dto.AuthorDTO;
import com.b1704721.bookmanagementsystem.dto.BookDTO;
import com.b1704721.bookmanagementsystem.service.IAuthorService;
import com.b1704721.bookmanagementsystem.service.IBookService;
import org.junit.*;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides smoke tests
 *
 * @author B1704721
 * @version 1.2
 * @since 20-Nov-2021
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

    /**
     * TC_TS01_01
     */
    @Test
    public void testCreateBook_success() throws Exception {
        System.out.println("Testing createBook_success.");

        // Given
        BookDTO inputBookDTO = new BookDTO();
        inputBookDTO.setTitle("Book Title 1");
        List<Long> inputAuthorIds = new ArrayList<>();
        inputAuthorIds.add(1L);
        inputAuthorIds.add(2L);
        inputBookDTO.setAuthorIds(inputAuthorIds);
        String inputJson = super.mapToJson(inputBookDTO);

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Book Title 1");
        List<Long> expectedAuthorIds = new ArrayList<>();
        expectedAuthorIds.add(1L);
        expectedAuthorIds.add(2L);
        expectedBookDTO.setAuthorIds(expectedAuthorIds);

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
        Assert.assertEquals(expectedBookDTO.getAuthorIds().size(), actualBookDTO.getAuthorIds().size());
        for (int i = 0; i < expectedBookDTO.getAuthorIds().size(); i++) {
            Assert.assertEquals(expectedBookDTO.getAuthorIds().get(i), actualBookDTO.getAuthorIds().get(i));
        }
    }

    /**
     * TC_TS05_01
     */
    @Test
    public void testCreateAuthor_success() throws Exception {
        System.out.println("Testing createAuthor_success.");

        // Given
        AuthorDTO inputAuthorDTO = new AuthorDTO();
        inputAuthorDTO.setName("Author Name 1");
        List<Long> inputBookIds = new ArrayList<>();
        inputBookIds.add(1L);
        inputBookIds.add(2L);
        inputAuthorDTO.setBookIds(inputBookIds);
        String inputJson = super.mapToJson(inputAuthorDTO);

        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Author Name 1");
        List<Long> expectedBookIds = new ArrayList<>();
        expectedBookIds.add(1L);
        expectedBookIds.add(2L);
        expectedAuthorDTO.setBookIds(expectedBookIds);

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
        Assert.assertEquals(expectedAuthorDTO.getBookIds().size(), actualAuthorDTO.getBookIds().size());
        for (int i = 0; i < expectedAuthorDTO.getBookIds().size(); i++) {
            Assert.assertEquals(expectedAuthorDTO.getBookIds().get(i), actualAuthorDTO.getBookIds().get(i));
        }
    }

}
