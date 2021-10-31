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
 * Provides smoke tests
 *
 * @author B1704721
 * @version 1.1
 * @since 31-Oct-2021
 */
public class SmokeTest extends AbstractControllerTest {

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
    public void testGetBookById_invalidId() throws Exception {
        System.out.println("Testing getBookById_invalidId.");

        // Given

        // When
        String uri = "/books/0";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(404, response.getStatus());

        Mockito.verify(bookService, Mockito.times(1)).getRecordById(Mockito.anyLong());

        Assert.assertEquals("", response.getContentAsString());
    }

}
