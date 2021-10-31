package com.b1704721.bookmanager.controller;

import com.b1704721.bookmanager.dto.BookDTO;
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
 * Tests BookController
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
 */
public class BookControllerTest extends AbstractControllerTest {

    @MockBean
    private IBookService bookService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of BookControllerTest.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of BookControllerTest.");
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
    public void testGetBookById_success() throws Exception {
        System.out.println("Testing getBookById_success.");

        // Given
        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle("Book Title 1");

        Mockito.when(bookService.getRecordById(Mockito.anyLong())).thenReturn(expectedBookDTO);

        // When
        String uri = "/books/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(200, response.getStatus());

        Mockito.verify(bookService, Mockito.times(1)).getRecordById(Mockito.anyLong());

        BookDTO actualBookDTO = super.mapFromJson(response.getContentAsString(), BookDTO.class);
        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
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

        Assert.assertEquals(200, response.getStatus());

        Mockito.verify(bookService, Mockito.times(1)).saveRecord(Mockito.any());

        BookDTO actualBookDTO = super.mapFromJson(response.getContentAsString(), BookDTO.class);
        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
    }

    @Test
    public void testUpdateBook_success() throws Exception {
        System.out.println("Testing updateBook_success.");

        // Given
        BookDTO inputBookDTO = new BookDTO();
        inputBookDTO.setTitle("Updated Book Title 1");
        String inputJson = super.mapToJson(inputBookDTO);

        BookDTO expectedBookDTO = new BookDTO();
        expectedBookDTO.setId(1L);
        expectedBookDTO.setTitle(inputBookDTO.getTitle());

        Mockito.when(bookService.updateRecord(Mockito.any())).thenReturn(expectedBookDTO);

        // When
        String uri = "/books";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(bookService, Mockito.times(1)).updateRecord(Mockito.any());

        Assert.assertEquals(200, response.getStatus());

        BookDTO actualBookDTO = super.mapFromJson(response.getContentAsString(), BookDTO.class);
        Assert.assertEquals(expectedBookDTO.getId(), actualBookDTO.getId());
        Assert.assertEquals(expectedBookDTO.getTitle(), actualBookDTO.getTitle());
    }

    @Test
    public void testDeleteBookById_success() throws Exception {
        System.out.println("Testing deleteBook_success.");

        // Given
        Mockito.doNothing().when(bookService).deleteRecordById(Mockito.anyLong());

        // When
        String uri = "/books/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(bookService, Mockito.times(1)).deleteRecordById(Mockito.anyLong());

        Assert.assertEquals(200, response.getStatus());

        Assert.assertEquals("", response.getContentAsString());
    }

}
