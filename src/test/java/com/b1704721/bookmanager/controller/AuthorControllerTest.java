package com.b1704721.bookmanager.controller;

import com.b1704721.bookmanager.dto.AuthorDTO;
import com.b1704721.bookmanager.service.IAuthorService;
import org.junit.*;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Tests AuthorController
 *
 * @author B1704721
 * @version 1.0
 * @since 03-Oct-2021
 */
public class AuthorControllerTest extends AbstractControllerTest {

    @MockBean
    private IAuthorService authorService;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start of AuthorControllerTest.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("End of AuthorControllerTest.");
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
    public void testGetAuthorById_success() throws Exception {
        System.out.println("Testing getAuthorById_success.");

        // Given
        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Author Name 1");

        Mockito.when(authorService.getRecordById(Mockito.anyLong())).thenReturn(expectedAuthorDTO);

        // When
        String uri = "/authors/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(200, response.getStatus());

        Mockito.verify(authorService, Mockito.times(1)).getRecordById(Mockito.anyLong());

        AuthorDTO actualAuthorDTO = super.mapFromJson(response.getContentAsString(), AuthorDTO.class);
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
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

        Assert.assertEquals(200, response.getStatus());

        Mockito.verify(authorService, Mockito.times(1)).saveRecord(Mockito.any());

        AuthorDTO actualAuthorDTO = super.mapFromJson(response.getContentAsString(), AuthorDTO.class);
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
    }

    @Test
    public void testUpdateAuthor_success() throws Exception {
        System.out.println("Testing updateAuthor_success.");

        // Given
        AuthorDTO inputAuthorDTO = new AuthorDTO();
        inputAuthorDTO.setName("Updated Author Name 1");
        String inputJson = super.mapToJson(inputAuthorDTO);

        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName(inputAuthorDTO.getName());

        Mockito.when(authorService.updateRecord(Mockito.any())).thenReturn(expectedAuthorDTO);

        // When
        String uri = "/authors";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(authorService, Mockito.times(1)).updateRecord(Mockito.any());

        Assert.assertEquals(200, response.getStatus());

        AuthorDTO actualAuthorDTO = super.mapFromJson(response.getContentAsString(), AuthorDTO.class);
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
    }

    @Test
    public void testDeleteAuthorById_success() throws Exception {
        System.out.println("Testing deleteAuthor_success.");

        // Given
        Mockito.doNothing().when(authorService).deleteRecordById(Mockito.anyLong());

        // When
        String uri = "/authors/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uri).accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(authorService, Mockito.times(1)).deleteRecordById(Mockito.anyLong());

        Assert.assertEquals(200, response.getStatus());

        Assert.assertEquals("", response.getContentAsString());
    }

}
