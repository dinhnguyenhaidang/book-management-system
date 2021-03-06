package com.b1704721.bookmanagementsystem.controller;

import com.b1704721.bookmanagementsystem.dto.AuthorDTO;
import com.b1704721.bookmanagementsystem.service.IAuthorService;
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
 * Tests AuthorController
 *
 * @author B1704721
 * @version 1.1
 * @since 20-Nov-2021
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

        String endpoint = "/authors";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(endpoint).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);

        // When
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

    /**
     * TC_TS06_01
     */
    @Test
    public void testGetAuthorById_success() throws Exception {
        System.out.println("Testing getAuthorById_success.");

        // Given
        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Author Name 1");
        List<Long> expectedBookIds = new ArrayList<>();
        expectedBookIds.add(1L);
        expectedBookIds.add(2L);
        expectedAuthorDTO.setBookIds(expectedBookIds);

        Mockito.when(authorService.getRecordById(Mockito.anyLong())).thenReturn(expectedAuthorDTO);

        String endpoint = "/authors/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(endpoint).accept(MediaType.APPLICATION_JSON_VALUE);

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(200, response.getStatus());

        Mockito.verify(authorService, Mockito.times(1)).getRecordById(Mockito.anyLong());

        AuthorDTO actualAuthorDTO = super.mapFromJson(response.getContentAsString(), AuthorDTO.class);
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
        Assert.assertEquals(expectedAuthorDTO.getBookIds().size(), actualAuthorDTO.getBookIds().size());
        for (int i = 0; i < expectedAuthorDTO.getBookIds().size(); i++) {
            Assert.assertEquals(expectedAuthorDTO.getBookIds().get(i), actualAuthorDTO.getBookIds().get(i));
        }
    }

    /**
     * TC_TS06_02
     */
    @Test
    public void testGetAuthorById_invalidId() throws Exception {
        System.out.println("Testing getAuthorById_invalidId.");

        // Given
        String endpoint = "/authors/0";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(endpoint).accept(MediaType.APPLICATION_JSON_VALUE);

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertEquals(404, response.getStatus());

        Mockito.verify(authorService, Mockito.times(1)).getRecordById(Mockito.anyLong());

        Assert.assertEquals("", response.getContentAsString());
    }

    /**
     * TC_TS07_01
     */
    @Test
    public void testUpdateAuthor_success() throws Exception {
        System.out.println("Testing updateAuthor_success.");

        // Given
        AuthorDTO inputAuthorDTO = new AuthorDTO();
        inputAuthorDTO.setId(1L);
        inputAuthorDTO.setName("Updated Author Name 1");
        List<Long> inputBookIds = new ArrayList<>();
        inputBookIds.add(1L);
        inputBookIds.add(2L);
        inputAuthorDTO.setBookIds(inputBookIds);
        String inputJson = super.mapToJson(inputAuthorDTO);

        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Updated Author Name 1");
        List<Long> expectedBookIds = new ArrayList<>();
        expectedBookIds.add(1L);
        expectedBookIds.add(2L);
        expectedAuthorDTO.setBookIds(expectedBookIds);

        Mockito.when(authorService.updateRecord(Mockito.any())).thenReturn(expectedAuthorDTO);

        String endpoint = "/authors";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(endpoint).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(authorService, Mockito.times(1)).updateRecord(Mockito.any());

        Assert.assertEquals(200, response.getStatus());

        AuthorDTO actualAuthorDTO = super.mapFromJson(response.getContentAsString(), AuthorDTO.class);
        Assert.assertEquals(expectedAuthorDTO.getId(), actualAuthorDTO.getId());
        Assert.assertEquals(expectedAuthorDTO.getName(), actualAuthorDTO.getName());
        Assert.assertEquals(expectedAuthorDTO.getBookIds().size(), actualAuthorDTO.getBookIds().size());
        for (int i = 0; i < expectedAuthorDTO.getBookIds().size(); i++) {
            Assert.assertEquals(expectedAuthorDTO.getBookIds().get(i), actualAuthorDTO.getBookIds().get(i));
        }
    }

    /**
     * TC_TS07_02
     */
    @Test
    public void testUpdateAuthor_invalidId() throws Exception {
        System.out.println("Testing updateAuthor_invalidId.");

        // Given
        AuthorDTO inputAuthorDTO = new AuthorDTO();
        inputAuthorDTO.setId(0L);
        inputAuthorDTO.setName("Updated Author Name 0");
        String inputJson = super.mapToJson(inputAuthorDTO);

        String endpoint = "/authors";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(endpoint).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson);

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(authorService, Mockito.times(1)).updateRecord(Mockito.any());

        Assert.assertEquals(404, response.getStatus());

        Assert.assertEquals("", response.getContentAsString());
    }

    /**
     * TC_TS08_01
     */
    @Test
    public void testDeleteAuthorById_success() throws Exception {
        System.out.println("Testing deleteAuthor_success.");

        // Given
        AuthorDTO expectedAuthorDTO = new AuthorDTO();
        expectedAuthorDTO.setId(1L);
        expectedAuthorDTO.setName("Author Name 1");

        Mockito.when(authorService.getRecordById(Mockito.anyLong())).thenReturn(expectedAuthorDTO);
        Mockito.doNothing().when(authorService).deleteRecordById(Mockito.anyLong());

        String endpoint = "/authors/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(endpoint).accept(MediaType.APPLICATION_JSON_VALUE);

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(authorService, Mockito.times(1)).getRecordById(Mockito.anyLong());
        Mockito.verify(authorService, Mockito.times(1)).deleteRecordById(Mockito.anyLong());

        Assert.assertEquals(200, response.getStatus());

        Assert.assertEquals("", response.getContentAsString());
    }

    /**
     * TC_TS08_02
     */
    @Test
    public void testDeleteAuthorById_invalidId() throws Exception {
        System.out.println("Testing deleteAuthor_invalidId.");

        // Given
        String endpoint = "/authors/0";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(endpoint).accept(MediaType.APPLICATION_JSON_VALUE);

        // When
        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        // Then
        MockHttpServletResponse response = mvcResult.getResponse();

        Mockito.verify(authorService, Mockito.times(1)).getRecordById(Mockito.anyLong());
        Mockito.verify(authorService, Mockito.times(0)).deleteRecordById(Mockito.anyLong());

        Assert.assertEquals(404, response.getStatus());

        Assert.assertEquals("", response.getContentAsString());
    }

}
